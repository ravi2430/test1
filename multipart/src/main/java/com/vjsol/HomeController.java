package com.vjsol;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/inAuthTest", method = RequestMethod.POST, consumes = { "multipart/form-data;" })
	public @ResponseBody String handleFileUpload(
			@RequestParam(value = "message", required = true) MultipartFile jsonFile,

			MultipartHttpServletRequest request, ModelAndView modelAndView) {
		String encodedPayload = "Not able to decode Payload";
		try {

			JsonReader reader = Json.createReader(jsonFile.getInputStream());
			JsonObject jsonObject = reader.readObject();
			JsonValue jsonValue = jsonObject.get("message");

			System.out.println(jsonValue.toString());
			String a = jsonValue.toString().replaceAll("\"", "");
			encodedPayload = new String(Base64.decodeBase64(a.getBytes(StandardCharsets.UTF_8)));
			System.out.println(encodedPayload);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedPayload;

	}
}
