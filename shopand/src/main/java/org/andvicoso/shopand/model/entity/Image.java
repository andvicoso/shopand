package org.andvicoso.shopand.model.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.andvicoso.shopand.model.entity.base.AbstractEntity;

@Entity
@Table(name = "image")
public class Image extends AbstractEntity {
	@NotNull
	private String mimeType;
	@Lob
	@NotNull
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public InputStream getAsStream() {
		return data != null ? new ByteArrayInputStream(data) : null;
	}

	public String getType() {
		return getType(mimeType);
	}

	public static String getType(String pMimeType) {
		int begin = pMimeType.indexOf("/");
		int length = pMimeType.length();
		return pMimeType.substring(begin + 1, length);
	}

}
