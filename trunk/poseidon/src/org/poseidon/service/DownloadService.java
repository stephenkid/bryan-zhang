package org.poseidon.service;

import java.util.List;

import org.poseidon.pojo.Person;

public interface DownloadService {
	public void createTestData();
	
	public List<Person> findPerson(Person cond);
	
	public void generateBigDataFile();
}
