package com.microsoft.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.entity.MicrosoftUpdate;

public class MicrosoftUpdateJson {

    @JsonProperty("ID")
    public String id;
    @JsonProperty("Alias")
    public String alias;
    @JsonProperty("DocumentTitle")
    public String documentTitle;
    @JsonProperty("Severity")
    public String severity;
    @JsonProperty("InitialReleaseDate")
    public Date initialReleaseDate;
    @JsonProperty("CurrentReleaseDate")
    public Date currentReleaseDate;
    @JsonProperty("CvrfUrl")
    public String cvrfUrl;

    public MicrosoftUpdate convertToEntity() {
	MicrosoftUpdate microsoftUpdate = new MicrosoftUpdate();
	microsoftUpdate.setIdMicrosoftUpdate(id);
	microsoftUpdate.setAlias(alias);
	microsoftUpdate.setDocumentTitle(documentTitle);
	microsoftUpdate.setSeverity(severity);
	microsoftUpdate.setInitialReleaseDate(initialReleaseDate);
	microsoftUpdate.setCurrentReleaseDate(currentReleaseDate);
	microsoftUpdate.setCvrfUrl(cvrfUrl);

	return microsoftUpdate;
    }
}