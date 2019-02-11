package com.drl.myday.test.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drl.myday.models.AdoptionMatrixPoint;
import com.drl.myday.models.AdoptionMatrixPointsType;
import com.drl.myday.models.BaseSource;
import com.drl.myday.models.Status;
import com.drl.myday.models.Task;
import com.drl.myday.models.TaskType;
import com.drl.myday.models.User;
import com.drl.myday.models.UserAdoption;
import com.drl.myday.repository.AdoptionMatrixPointRepository;
import com.drl.myday.repository.UserAdoptionRepository;
import com.drl.myday.security.ContextService;
import com.drl.myday.services.UserAdoptionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserAdoptionServiceTest {

	@Mock
	private ContextService contextService;

	@Mock
	private UserAdoptionRepository userAdoptionRepository;

	@Mock
	private AdoptionMatrixPointRepository adaptionPointsRepository;

	@Mock
	private UserAdoptionServiceImpl userAdoptionServiceImpl;

	@Test
	public void getAdoptionPointsOnCreate() {
		contextService.setCurrentUser(new User(1618));
		UserAdoption userAdoption = new UserAdoption();
		userAdoption.setSource(new BaseSource());
		userAdoption.setCreatedBy(contextService.getCurrentUser());
		userAdoption.setUser(contextService.getCurrentUser());

		when(userAdoptionRepository.findUserAdoptionBySourceAndType(1, 2)).thenReturn(userAdoption);
		ArgumentCaptor valueCapture = ArgumentCaptor.forClass(AdoptionMatrixPointsType.class);

		doNothing().when(userAdoptionServiceImpl).getAdoptionPointsOnCreate(anyObject(),
			(AdoptionMatrixPointsType) valueCapture.capture());
		userAdoptionServiceImpl.getAdoptionPointsOnCreate(new BaseSource(), AdoptionMatrixPointsType.CREATE_NOTE);

		verify(userAdoptionServiceImpl, times(1)).getAdoptionPointsOnCreate(anyObject(), anyObject());

		assertEquals(AdoptionMatrixPointsType.CREATE_NOTE, valueCapture.getValue());

	}

	@Test
	public void getPointsOnNudge() {

		contextService.setCurrentUser(new User(1618));
		UserAdoption userAdoption = new UserAdoption();
		userAdoption.setCreatedBy(contextService.getCurrentUser());
		userAdoption.setUser(contextService.getCurrentUser());
		userAdoption.setTask(new Task());

		when(userAdoptionRepository.save(userAdoption)).thenReturn(userAdoption);

		ArgumentCaptor valueCapture = ArgumentCaptor.forClass(AdoptionMatrixPointsType.class);
		ArgumentCaptor valueCapture2 = ArgumentCaptor.forClass(TaskType.class);

		doNothing().when(userAdoptionServiceImpl).getPointsOnNudge(anyObject(), (TaskType) valueCapture2.capture(),
				(AdoptionMatrixPointsType) valueCapture.capture());
		userAdoptionServiceImpl.getPointsOnNudge(new Task(), TaskType.TASK, AdoptionMatrixPointsType.CREATE_NOTE);

		verify(userAdoptionServiceImpl, times(1)).getPointsOnNudge(anyObject(), anyObject(), anyObject());

		assertEquals(AdoptionMatrixPointsType.CREATE_NOTE, valueCapture.getValue());
		assertEquals(TaskType.TASK, valueCapture2.getValue());

	}

	@Test
	public void getAdoptionPointsOnTaskCreate() {

		contextService.setCurrentUser(new User(1618));
		User user = contextService.getCurrentUser();
		AdoptionMatrixPoint adoption = new AdoptionMatrixPoint();
		adoption.setCreatedBy(user);
		adoption.setStatus(Status.ACTIVE);
		adoption.setType(AdoptionMatrixPointsType.PERSONAL_TASK);

		when(adaptionPointsRepository.findByType(AdoptionMatrixPointsType.PERSONAL_TASK)).thenReturn(adoption);

		doNothing().when(userAdoptionServiceImpl).getAdoptionPointsOnTaskCreate(anyObject());
		userAdoptionServiceImpl.getAdoptionPointsOnTaskCreate(new Task());

		verify(userAdoptionServiceImpl, times(1)).getAdoptionPointsOnTaskCreate(anyObject());

	}

}
