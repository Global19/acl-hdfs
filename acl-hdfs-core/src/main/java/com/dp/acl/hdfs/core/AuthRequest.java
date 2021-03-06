package com.dp.acl.hdfs.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Writable;

public class AuthRequest implements Serializable, Writable{
	
	private static final long serialVersionUID = 3708043724071024047L;
	
	public static final int NONE = -1;
	public static final int ACCESS_MODE_READ = 0;
	public static final int ACCESS_MODE_WRITE = 1;
	
	private String user;
	private String tableName;
	private int accessMode = NONE;

	public AuthRequest(){}
	
	public AuthRequest(String user, String tableName, int accessMode) {
		super();
		this.user = user;
		this.tableName = tableName;
		this.accessMode = accessMode;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getAccessMode() {
		return accessMode;
	}
	public void setAccessMode(int accessMode) {
		this.accessMode = accessMode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessMode;
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthRequest other = (AuthRequest) obj;
		if (accessMode != other.accessMode)
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AuthRequest [user=" + user + ", tableName=" + tableName
				+ ", accessMode=" + accessMode + "]";
	}

	public boolean valid(){
		if(accessMode == NONE || StringUtils.isEmpty(tableName))
			return false;
		return true;
	}

	public void readFields(DataInput in) throws IOException {
		tableName = in.readUTF();
		accessMode = in.readInt();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(tableName);
		out.writeInt(accessMode);
	}
}
