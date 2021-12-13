package com.alkemy.ong.model.response.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Data
public class DecodedToken {
    public String sub;
    public String exp;
    public String iat;

    public static DecodedToken getDecoded(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(Base64.getDecoder().decode(b64payload), "UTF-8");

        return new Gson().fromJson(jsonString, DecodedToken.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
