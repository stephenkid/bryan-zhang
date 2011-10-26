package org.poseidon.service;

import java.util.List;

import org.poseidon.dto.DownloadFileDto;
import org.poseidon.pojo.DownloadFile;
import org.poseidon.pojo.Person;

public interface DownloadService {
	public void createTestData();
	
	public List<Person> findPerson(Person cond);
	
	public void generateBigDataFile();
	
	public List<DownloadFile> findDownloadFileList(DownloadFileDto dto, int page, int rows);
}
