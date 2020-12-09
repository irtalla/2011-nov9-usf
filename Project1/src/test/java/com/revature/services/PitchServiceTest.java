package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.PitchDAO;
import com.revature.models.Pitch;

@ExtendWith(MockitoExtension.class)
class PitchServiceTest {
	@Mock
	static PitchDAO pitchDao;

	@InjectMocks
	static PitchServiceImpl pitchServ;
	
	static Set<Pitch> pitchMock = new HashSet<>();
	static Integer pitchSeqMock = 1;

}
