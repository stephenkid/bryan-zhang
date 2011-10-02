package org.poseidon.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DownloadService {
	public void createTestData();
}
