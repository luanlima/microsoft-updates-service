package com.microsoft.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import com.microsoft.dao.MicrosoftUpdateDaoImpl;
import com.microsoft.entity.MicrosoftUpdate;
import com.microsoft.json.MicrosoftUpdateRootJson;

public class MicrosoftUpdateTimerTask extends TimerTask {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static final String URL_MICROSOFT_UPDATES = "https://api.msrc.microsoft.com/cvrf/v2.0/updates";

    @Override
    public void run() {
	try {
	    logger.log(Level.INFO, "Data/hora inicio consumo API Microsoft: " + sdf.format(new Date()));

	    List<MicrosoftUpdate> listMicrosoftUpdate = consumeMicrosoftApi();

	    saveListMicrosoftUpdate(listMicrosoftUpdate);

	    logger.log(Level.INFO, "API Microsoft consumida com sucesso.");
	    logger.log(Level.INFO, "Data/hora fim consumo API Microsoft: " + sdf.format(new Date()));
	} catch (Exception e) {
	    e.printStackTrace();
	    this.cancel();
	}
    }

    public List<MicrosoftUpdate> consumeMicrosoftApi() {
	Client client = null;

	try {
	    client = ClientBuilder.newClient();
	    client.register(JacksonJsonProvider.class);
	    try (Response response = client.target(URL_MICROSOFT_UPDATES).request(MediaType.APPLICATION_JSON_TYPE)
		    .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED).get()) {

		MicrosoftUpdateRootJson microsoftUpdateRootJson = response.readEntity(MicrosoftUpdateRootJson.class);

		List<MicrosoftUpdate> listMicrosoftUpdate = microsoftUpdateRootJson.value.stream()
			.map(value -> value.convertToEntity()).collect(Collectors.toList());

		return listMicrosoftUpdate;
	    }

	} finally {
	    if (client != null) {
		client.close();
	    }
	}
    }

    private void saveListMicrosoftUpdate(List<MicrosoftUpdate> listMicrosoftUpdate) throws Exception {
	MicrosoftUpdateDaoImpl microsoftUpdateDaoImpl = MicrosoftUpdateDaoImpl.getInstance();
	for (MicrosoftUpdate microsoftUpdate : listMicrosoftUpdate) {
	    if (microsoftUpdateDaoImpl.getByIdMicrosoftUpdate(microsoftUpdate.getIdMicrosoftUpdate()) == null) {
		microsoftUpdateDaoImpl.saveOrUpdate(microsoftUpdate);
	    }
	}
    }
}