package org.poseidon.service;

import java.util.List;

import org.poseidon.pojo.Person;

public interface DownloadService {
	public void createTestData() throws Exception;
	
	public List<Person> findTotalPerson() throws Exception;
	
	public void generateBigDataFile() throws Exception;
}
