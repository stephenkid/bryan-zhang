package org.poseidon.service;

import java.util.List;
import java.util.Map;

import org.poseidon.dto.DownloadFileDto;
import org.poseidon.pojo.Person;

public interface DownloadService {
	public void createTestData();
	
	public List<Person> findPerson(Person cond);
	
	public void generateBigDataFile();
	
	public Map<String, ?> findDownloadFileList(DownloadFileDto dto, int page, int rows);
}
