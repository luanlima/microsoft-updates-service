package com.microsoft.json;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class MicrosoftUpdateRootJson {

    @JsonProperty("@odata.context")
    public String odataContext;
    public List<MicrosoftUpdateJson> value;
}