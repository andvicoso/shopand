package org.andvicoso.shopand.controller.servlet.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class MultipartContentBaseServlet extends BaseServlet {

	protected List<FileItem> getItems(HttpServletRequest request) {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		List<FileItem> items = Collections.emptyList();

		try {
			items = (List<FileItem>) sfu.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		return items;
	}

	protected boolean isRequestParametersValid(List<FileItem> items) {
		Map<String, String> msgs = new HashMap<String, String>();

		verifyParameters(items, msgs, getRequiredParameters(),
				REQUIRED_FIELD_MSG);

		return msgs.isEmpty();
	}

	private void verifyParameters(List<FileItem> items,
			Map<String, String> msgs, String[] parameters, String fieldMsg) {
		for (String fieldName : parameters) {
			boolean found = false;
			for (FileItem fi : items) {
				if (fi.getFieldName().equals(fieldName))
					found = true;
			}
			if (!found)
				msgs.put(fieldName, fieldMsg);
		}
	}

	protected FileItem getParam(List<FileItem> items, String fieldName) {
		for (FileItem fi : items) {
			if (fi.getFieldName().equals(fieldName))
				return fi;
		}

		return null;
	}
}
