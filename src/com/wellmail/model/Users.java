package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ”√ªß±Ì
 * @author Administrator
 *
 */
public class Users {

	private String username;
	
	private String pwdHash;
	
	private String pwdAlgorithm;
	
	private int useForwarding;
	
	private String forwardDestination;
	
	private int useAlias;
	
	private String alias;
	
	//folder set
	private Set<Folder> folders = new HashSet<Folder>();
	
	//webfile set
	private Set<WebFile> webfiles = new HashSet<WebFile>();
	
	//filetype set
	private Set<FileType> filetypes = new HashSet<FileType>();
	
	//notebook set
	private Set<NoteBook> notebooks = new HashSet<NoteBook>();
	
	//notetype set
	private Set<NoteType> notetypes = new HashSet<NoteType>();
	
	//contact set
	private Set<Contact> contacts = new HashSet<Contact>();
	
	//contactgroup set
	private Set<ContactGroup> contactgroups = new HashSet<ContactGroup>();
	
	//email set
	private Set<Email> emails = new HashSet<Email>();
	
	//othermailbox set
	private Set<OtherMailBox> othermailboxs = new HashSet<OtherMailBox>();

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<ContactGroup> getContactgroups() {
		return contactgroups;
	}

	public void setContactgroups(Set<ContactGroup> contactgroups) {
		this.contactgroups = contactgroups;
	}

	public Set<NoteType> getNotetypes() {
		return notetypes;
	}

	public void setNotetypes(Set<NoteType> notetypes) {
		this.notetypes = notetypes;
	}

	public Set<NoteBook> getNotebooks() {
		return notebooks;
	}

	public void setNotebooks(Set<NoteBook> notebooks) {
		this.notebooks = notebooks;
	}

	public Set<FileType> getFiletypes() {
		return filetypes;
	}

	public void setFiletypes(Set<FileType> filetypes) {
		this.filetypes = filetypes;
	}

	public Set<WebFile> getWebfiles() {
		return webfiles;
	}

	public void setWebfiles(Set<WebFile> webfiles) {
		this.webfiles = webfiles;
	}

	public Set<Folder> getFolders() {
		return folders;
	}

	public void setFolders(Set<Folder> folders) {
		this.folders = folders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public String getPwdAlgorithm() {
		return pwdAlgorithm;
	}

	public void setPwdAlgorithm(String pwdAlgorithm) {
		this.pwdAlgorithm = pwdAlgorithm;
	}

	public int getUseForwarding() {
		return useForwarding;
	}

	public void setUseForwarding(int useForwarding) {
		this.useForwarding = useForwarding;
	}

	public String getForwardDestination() {
		return forwardDestination;
	}

	public void setForwardDestination(String forwardDestination) {
		this.forwardDestination = forwardDestination;
	}

	public int getUseAlias() {
		return useAlias;
	}

	public void setUseAlias(int useAlias) {
		this.useAlias = useAlias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Set<OtherMailBox> getOthermailboxs() {
		return othermailboxs;
	}

	public void setOthermailboxs(Set<OtherMailBox> othermailboxs) {
		this.othermailboxs = othermailboxs;
	}

}
