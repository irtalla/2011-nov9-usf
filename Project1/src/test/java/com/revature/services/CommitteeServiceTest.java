package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.CommitteeDAO;
import com.revature.models.Committee;

@ExtendWith(MockitoExtension.class)
public class CommitteeServiceTest {
	@Mock
	static CommitteeDAO committeeDao;
	
	@InjectMocks
	static CommitteeServiceImpl committeeServ;
	
	static Set<Committee> committeesMock = new HashSet<>();
	static Integer committeeSeqMock = 1;
}
