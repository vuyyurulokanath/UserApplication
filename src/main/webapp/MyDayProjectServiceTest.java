package com.drl.myday.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drl.myday.connector.services.IProjectService;
import com.drl.myday.connector.services.ITaskService;
import com.drl.myday.models.ServiceIdentifier;
import com.drl.myday.models.Status;
import com.drl.myday.models.Task;
import com.drl.myday.models.TaskPriority;
import com.drl.myday.models.TaskProgress;
import com.drl.myday.models.TaskType;
import com.drl.myday.models.User;
import com.drl.myday.repository.ActivityLogRepository;
import com.drl.myday.repository.ProjectMemberRepository;
import com.drl.myday.repository.ProjectRepository;
import com.drl.myday.repository.TaskRepository;
import com.drl.myday.services.ActivityLogService;
import com.drl.myday.services.INotificationService;
import com.drl.myday.services.IUserAdoptionService;
import com.drl.myday.services.MyDayProjectService;
import com.drl.myday.services.ResourceAuthenticator;
//import com.drl.myday.testw.util.TestUtil;
import com.drl.myday.vo.BaseResponse;
import com.drl.myday.vo.ProjectVo;
import com.drl.myday.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
public class MyDayProjectServiceTest {
//	response.setData(fetchQuestionVo);
	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private INotificationService notificationService;

	@Mock
	private ActivityLogService activityLogService;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private IProjectService projectService;

	@Mock
	private ITaskService taskService;

	@Mock
	private ActivityLogRepository activityLogRepository;

	@Mock
	private IUserAdoptionService userAdoptionService;

	@Mock
	private ResourceAuthenticator authenticationManager;

	@Mock
	private ProjectMemberRepository projectMemberRepository;

	@Mock
	private MyDayProjectService myDayProjectService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getProjectDetailsFromMyDay() {
		UserVo userVo = new UserVo();
		userVo.setId("1");
		BaseResponse<ProjectVo> response = new BaseResponse<>();
		ProjectVo projectVo = new ProjectVo();
		projectVo.setTitle("Project 1");
		projectVo.setTaskCount(10);
		projectVo.setStatus(Status.ACTIVE);
		projectVo.setOwner(userVo);
		response.setData(projectVo);
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK);

		Mockito.when(myDayProjectService.getProjectDetailsFromMyDay(anyString(), anyObject(), anyObject()))
				.thenReturn(response);
		myDayProjectService.getProjectDetailsFromMyDay(anyString(), anyObject(), anyObject());
		verify(myDayProjectService, times(1)).getProjectDetailsFromMyDay(anyString(), anyObject(), anyObject());

		assertNotNull(response);
		assertThat("Success").isEqualTo(response.getMessage());
		assertThat("Project 1").isEqualTo(response.getData().getTitle());
		assertThat(10).isEqualTo(response.getData().getTaskCount());
		assertThat(Status.ACTIVE).isEqualTo(response.getData().getStatus());
	}
////
////	@Test
////	public void deleteProjectFromMyDay() {
////		BaseResponse<Void> response = new BaseResponse<Void>();
////		response.setMessage("Mom deleted SuccessFully");
////		response.setStatus(200);
////
////		Mockito.when(myDayProjectService.deleteProjectFromMyDay(anyString(), anyObject(), anyObject()))
////				.thenReturn(response);
////
////		myDayProjectService.deleteProjectFromMyDay(anyString(), anyObject(), anyObject());
////		verify(myDayProjectService, times(1)).deleteProjectFromMyDay(anyString(), anyObject(), anyObject());
////		assertNotNull(response);
////		assertThat("Mom deleted SuccessFully").isEqualTo(response.getMessage());
////		assertThat(200).isEqualTo(response.getStatus());
////	}
////
////	@Test
////	public void copyProjectInMyDay() {
////		BaseResponse<Void> response = new BaseResponse<Void>();
////		response.setMessage("Project copied successful");
////		response.setStatus(200);
////
////		Task task = new Task("title", "Description", anyObject(), anyObject()TaskPriority.LOW, TaskProgress.NOTSTARTED, new Date(),
////				TaskType.TASK, new Date(), "Notes", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "1", null,
////				null, new User(1618), new Date(), new User(188), new Date(), Status.ACTIVE,
////				new ServiceIdentifier(1));
////
////		List<Task> list = new ArrayList<>();
////		list.add(task);
////		Mockito.when(taskRepository.findByReference(1, Status.ACTIVE)).thenReturn(list);
////		BaseResponse<Void> responses = myDayProjectService.copyProjectInMyDay("1", new User(1618),
////				new ServiceIdentifier(1));
////		Mockito.when(myDayProjectService.copyProjectInMyDay(anyString(), anyObject(), anyObject()))
////				.thenReturn(response);
////		assertNotNull(response);
////		assertThat("Project copied successful").isEqualTo(response.getMessage());
////		assertThat(200).isEqualTo(response.getStatus());
////	}
////
////	@Test
////	public void fetchAllProjectsFromMyDay() {
////		UserVo userVo = new UserVo();
////		userVo.setId("1");
////		BaseResponse<List<ProjectVo>> response = new BaseResponse<>();
////		List<ProjectVo> list = new ArrayList<>();
////		ProjectVo projectVo = new ProjectVo();
////		projectVo.setTitle("Project 3");
////		projectVo.setTaskCount(10);
////		projectVo.setStatus(Status.ACTIVE);
////		projectVo.setOwner(userVo);
////		list.add(projectVo);
////		response.setData(list);
////		response.setMessage("Success");
////		response.setStatus(TestUtil.OK);
////
////		Mockito.when(myDayProjectService.fetchAllProjectsFromMyDay(anyObject(), anyObject())).thenReturn(response);
////		assertNotNull(response);
////		assertThat("Success").isEqualTo(response.getMessage());
////		assertThat(200).isEqualTo(response.getStatus());
////		assertThat(10).isEqualTo(response.getData().get(0).getTaskCount());
////		assertThat(Status.ACTIVE).isEqualTo(response.getData().get(0).getStatus());
////
////	}
////
////	@Test
////	public void updateProjectStatusInMyDay() {
////		BaseResponse<Void> response = new BaseResponse<>();
////		response.setMessage("Success");
////		response.setStatus(200);
////
////		Mockito.when(myDayProjectService.updateProjectStatusInMyDay(anyString(), anyObject(), anyObject(), anyObject()))
////				.thenReturn(response);
////		myDayProjectService.updateProjectStatusInMyDay(anyString(), anyObject(), anyObject(), anyObject());
////		verify(myDayProjectService, times(1)).updateProjectStatusInMyDay(anyString(), anyObject(), anyObject(),
////				anyObject());
////		assertNotNull(response);
////		assertThat("Success").isEqualTo(response.getMessage());
////		assertThat(200).isEqualTo(response.getStatus());
////
////	}
////
////	@Test
////	public void createOrUpdateProjectInMyDay() {
////
////		ProjectVo projectVo = new ProjectVo();
////		UserVo user = new UserVo();
////		user.setId("1618");
////		projectVo.setTitle("Project 2");
////		projectVo.setTaskCount(10);
////		projectVo.setStatus(Status.ACTIVE);
////		projectVo.setOwner(user);
////		BaseResponse<ProjectVo> response = new BaseResponse<>();
////		response.setMessage("Success");
////		response.setStatus(200);
////
////		Mockito.when(myDayProjectService.createOrUpdateProjectInMyDay(anyObject(), anyObject(), anyObject()))
////				.thenReturn(response);
////		myDayProjectService.createOrUpdateProjectInMyDay(anyObject(), anyObject(), anyObject());
////		verify(myDayProjectService, times(1)).createOrUpdateProjectInMyDay(anyObject(), anyObject(), anyObject());
////
////		assertNotNull(response);
////		assertThat("Success").isEqualTo(response.getMessage());
////		assertThat(200).isEqualTo(response.getStatus());
////
////	}
////
}
