package com.simbirsoft.intership;

import com.simbirsoft.intership.model.*;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.ProjectStatus;
import com.simbirsoft.intership.model.enumaration.Role;
import com.simbirsoft.intership.model.enumaration.TaskStatus;
import com.simbirsoft.intership.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class SimbirsoftIntershipApplication {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final RealiseRepository realiseRepository;
    private final TaskRepository taskRepository;
    private final ProjectPermissionRepository projectPermissionRepository;
    private final PasswordEncoder passwordEncoder;

    public SimbirsoftIntershipApplication(UserRepository userRepository,
                                          ProjectRepository projectRepository,
                                          RealiseRepository realiseRepository,
                                          TaskRepository taskRepository,
                                          ProjectPermissionRepository projectPermissionRepository,
                                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.realiseRepository = realiseRepository;
        this.taskRepository = taskRepository;
        this.projectPermissionRepository = projectPermissionRepository;
        this.passwordEncoder = passwordEncoder;
        log.info("Adding different data for testing!");
        addTestData();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimbirsoftIntershipApplication.class, args);
    }

    //delete before relies
    private void addTestData() {
        //Добавляем юзеров
        User user = User.builder()
                .fullName("Иванов Иван Иванович")
                .roles(Set.of(Role.USER))
                .email("ivanov@mail.ru")
                .password(passwordEncoder.encode("1234567890"))
                .build();
        User user1 = User.builder()
                .fullName("Алексей Грозный")
                .roles(Set.of(Role.USER))
                .email("alex@mail.ru")
                .password(passwordEncoder.encode("1234567890"))
                .build();
        User user2 = User.builder()
                .fullName("Гарипов Артем")
                .roles(Set.of(Role.USER))
                .email("garipov@mail.ru")
                .password(passwordEncoder.encode("1234567890"))
                .build();
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        } else {
            user = userRepository.findByEmail(user.getEmail()).get();
        }
        if (userRepository.findByEmail(user1.getEmail()).isEmpty()) {
            userRepository.save(user1);
        } else {
            user1 = userRepository.findByEmail(user1.getEmail()).get();
        }
        if (userRepository.findByEmail(user2.getEmail()).isEmpty()) {
            userRepository.save(user2);
        } else {
            user2 = userRepository.findByEmail(user2.getEmail()).get();
        }
        //Добавляем проект
        Project project = Project.builder()
                .title("Проект Яндекс")
                .projectStatus(ProjectStatus.ACTIVE)
                .owner(user)
                .build();
        projectRepository.save(project);
        //Добавляем доступы пользователям к проекту
        ProjectPermission projectPermissionForOwner = ProjectPermission.builder()
                .permissions(Set.of(Permission.Leading))
                .project(project)
                .user(user)
                .build();
        projectPermissionRepository.save(projectPermissionForOwner);

        ProjectPermission projectPermissionUsual = ProjectPermission.builder()
                .permissions(Set.of(Permission.Usual))
                .project(project)
                .user(user1)
                .build();
        ProjectPermission projectPermissionLeading = ProjectPermission.builder()
                .permissions(Set.of(Permission.Leading))
                .project(project)
                .user(user2)
                .build();
        projectPermissionRepository.save(projectPermissionUsual);
        projectPermissionRepository.save(projectPermissionLeading);
        //Добавляем релизы
        Realise realise1 = Realise.builder()
                .project(project)
                .dateStart(Date.valueOf("2023-03-16"))
                .dateEnd(Date.valueOf("2023-03-20"))
                .title("Разрабатываем макет")
                .build();
        Realise realise2 = Realise.builder()
                .project(project)
                .dateStart(Date.valueOf("2023-03-21"))
                .dateEnd(Date.valueOf("2023-03-28"))
                .title("Делаем демо-версию")
                .build();
        realiseRepository.save(realise1);
        realiseRepository.save(realise2);

        //Добавляем задачи

        Task task = Task.builder()
                .realise(realise1)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .owner(user)
                .performer(user1)
                .project(project)
                .title("Назначить задачи")
                .build();
        Task task1 = Task.builder()
                .realise(realise1)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .owner(user1)
                .performer(user2)
                .project(project)
                .title("Сделать наброски")
                .build();
        Task task2 = Task.builder()
                .realise(realise1)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .owner(user1)
                .performer(user2)
                .project(project)
                .title("Сделать готовый проект")
                .build();
        Task task3 = Task.builder()
                .realise(realise2)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .owner(user)
                .performer(user1)
                .project(project)
                .title("Начать разработку")
                .build();
        Task task4 = Task.builder()
                .realise(realise2)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .owner(user1)
                .performer(user2)
                .project(project)
                .title("Сделать отчёт")
                .build();
        taskRepository.save(task);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
    }


}
