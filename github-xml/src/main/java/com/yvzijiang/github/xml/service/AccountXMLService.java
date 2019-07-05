package com.yvzijiang.github.xml.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yvzijiang.github.xml.dao.Account;
import com.yvzijiang.github.xml.exception.AccountXMLException;

/**
 * @author yvzijiang
 *
 */
@Service
public class AccountXMLService {

	private static final String ELEMENT_ROOT = "account-xml";
	private static final String ELEMENT_ACCOUNTS = "accounts";
	private static final String ELEMENT_ACCOUNT = "account";
	private static final String ELEMENT_ACCOUNT_ID = "id";
	private static final String ELEMENT_ACCOUNT_NAME = "name";
	private static final String ELEMENT_ACCOUNT_EMAIL = "email";
	private static final String ELEMENT_ACCOUNT_PASSWORD = "password";
	private static final String ELEMENT_ACCOUNT_ACTIVATED = "activated";

	@Value("${xml.file}")
	private String file;

	private SAXReader reader = new SAXReader();

	private Document readDocument() throws AccountXMLException {
		File dataFile = new File(file);

		if (!dataFile.exists()) {
			dataFile.getParentFile().mkdirs();
			Document doc = DocumentFactory.getInstance().createDocument();
			Element rootElement = doc.addElement(ELEMENT_ROOT);
			rootElement.addElement(ELEMENT_ACCOUNTS);
			writeDocument(doc);
		}
		try {
			return reader.read(new File(file));
		} catch (DocumentException e) {
			throw new AccountXMLException("Unable to read data xml", e);
		}
	}

	private void writeDocument(Document doc) throws AccountXMLException {
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
			writer.write(doc);
		} catch (IOException e) {
			throw new AccountXMLException("Unable to write data xml", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw new AccountXMLException("Unable to close data xml writer", e);
			}
		}
	}

	public Account readAccount(String id) throws AccountXMLException {
		Document doc = readDocument();
		Element accountsElement = doc.getRootElement().element(ELEMENT_ACCOUNTS);

		for (Element accountElement : (List<Element>) accountsElement.elements()) {
			if (accountElement.elementText(ELEMENT_ACCOUNT_ID).equals(id)) {
				return buildAccount(accountElement);
			}
		}
		return null;
	}

	public Account createAccount(Account account) throws AccountXMLException {
		Document doc = readDocument();
		Element accountElement = doc.getRootElement().element(ELEMENT_ACCOUNTS).addElement(ELEMENT_ACCOUNT);
		accountElement.addElement(ELEMENT_ACCOUNT_ID).addText(account.getId());
		accountElement.addElement(ELEMENT_ACCOUNT_NAME).addText(account.getName());
		accountElement.addElement(ELEMENT_ACCOUNT_EMAIL).addText(account.getEmail());
		accountElement.addElement(ELEMENT_ACCOUNT_PASSWORD).addText(account.getPassword());
		accountElement.addElement(ELEMENT_ACCOUNT_ACTIVATED).addText(account.isActivated() ? "true" : "false");
		writeDocument(doc);
		return account;
	}

	private Account buildAccount(Element element) {
		Account account = new Account();

		account.setId(element.elementText(ELEMENT_ACCOUNT_ID));
		account.setName(element.elementText(ELEMENT_ACCOUNT_NAME));
		account.setEmail(element.elementText(ELEMENT_ACCOUNT_EMAIL));
		account.setPassword(element.elementText(ELEMENT_ACCOUNT_PASSWORD));
		account.setActivated("true".equals(element.elementText(ELEMENT_ACCOUNT_ACTIVATED)) ? true : false);

		return account;
	}

}
