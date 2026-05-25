package com.hiringms.config;

import com.hiringms.model.Application;
import com.hiringms.model.Job;
import com.hiringms.model.User;
import com.hiringms.repository.ApplicationRepository;
import com.hiringms.repository.JobRepository;
import com.hiringms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        System.out.println("\n  === Seeding database with sample data... ===\n");

        // USERS
        User admin = userRepository.save(User.builder()
                .email("admin@test.com")
                .password(passwordEncoder.encode("admin123"))
                .fullName("Admin User")
                .phone("9000000001")
                .role(User.Role.ADMIN)
                .build());

        User omkar = userRepository.save(User.builder()
                .email("omkar@test.com")
                .password(passwordEncoder.encode("pass123"))
                .fullName("Omkar Deshpande")
                .phone("9000000002")
                .role(User.Role.CANDIDATE)
                .skills("Java, Spring Boot, REST APIs, SQL")
                .experience("3 years in backend development")
                .build());

        User prajwal = userRepository.save(User.builder()
                .email("prajwal@test.com")
                .password(passwordEncoder.encode("pass123"))
                .fullName("Prajwal Kumar")
                .phone("9000000003")
                .role(User.Role.CANDIDATE)
                .skills("React, JavaScript, HTML, CSS, Node.js")
                .experience("2 years in frontend development")
                .build());

        User manik = userRepository.save(User.builder()
                .email("manik@test.com")
                .password(passwordEncoder.encode("pass123"))
                .fullName("Manik Sharma")
                .phone("9000000004")
                .role(User.Role.CANDIDATE)
                .skills("Python, Machine Learning, TensorFlow, SQL")
                .experience("4 years in data science and ML")
                .build());

        User jaydeep = userRepository.save(User.builder()
                .email("jaydeep@test.com")
                .password(passwordEncoder.encode("pass123"))
                .fullName("Jaydeep Patil")
                .phone("9000000005")
                .role(User.Role.RECRUITER)
                .build());

        User smitha = userRepository.save(User.builder()
                .email("smitha@test.com")
                .password(passwordEncoder.encode("pass123"))
                .fullName("Smitha Rao")
                .phone("9000000006")
                .role(User.Role.RECRUITER)
                .build());

        // JOBS
        Job job1 = jobRepository.save(Job.builder()
                .title("Software Developer / Software Engineer")
                .company("TechCorp Solutions")
                .location("Bangalore, India")
                .salary("8,00,000 - 15,00,000")
                .type("FULL_TIME")
                .description("A software developer builds applications, websites, or systems using programming languages like Java, Python, or JavaScript. They write code, fix bugs, and add new features. Skills: Programming, data structures, problem solving, Git, databases. Focus: Building products (apps, platforms, systems).")
                .postedBy(jaydeep)
                .active(true)
                .build());

        Job job2 = jobRepository.save(Job.builder()
                .title("Frontend Developer")
                .company("PixelCraft Studios")
                .location("Pune, India")
                .salary("6,00,000 - 12,00,000")
                .type("FULL_TIME")
                .description("Frontend developers work on the user interface (UI) - what users see and interact with in a web or mobile app. They turn designs into interactive websites. Skills: HTML, CSS, JavaScript, React/Angular/Vue. Focus: UI/UX implementation.")
                .postedBy(jaydeep)
                .active(true)
                .build());

        Job job3 = jobRepository.save(Job.builder()
                .title("Backend Developer")
                .company("CloudNine Technologies")
                .location("Hyderabad, India")
                .salary("10,00,000 - 18,00,000")
                .type("FULL_TIME")
                .description("Backend developers handle the server-side logic, databases, and APIs that power applications. They make sure data is stored and retrieved properly. Skills: Java, Python, Node.js, SQL, APIs, system design basics. Focus: Logic, performance, and data handling.")
                .postedBy(jaydeep)
                .active(true)
                .build());

        Job job4 = jobRepository.save(Job.builder()
                .title("Full Stack Developer")
                .company("InnovateTech")
                .location("Mumbai, India")
                .salary("12,00,000 - 22,00,000")
                .type("FULL_TIME")
                .description("Full stack developers do both frontend and backend work. They can build a complete application from start to finish. Skills: Frontend + Backend skills, databases, deployment. Focus: End-to-end development.")
                .postedBy(smitha)
                .active(true)
                .build());

        Job job5 = jobRepository.save(Job.builder()
                .title("DevOps Engineer")
                .company("DeployFast Inc.")
                .location("Remote")
                .salary("14,00,000 - 25,00,000")
                .type("FULL_TIME")
                .description("DevOps engineers ensure smooth deployment and operation of software. They automate builds, testing, and server deployment. Skills: Linux, Docker, Kubernetes, CI/CD tools, cloud platforms (AWS/Azure/GCP). Focus: Automation + system reliability.")
                .postedBy(smitha)
                .active(true)
                .build());

        Job job6 = jobRepository.save(Job.builder()
                .title("Data Scientist")
                .company("DataMinds Analytics")
                .location("Bangalore, India")
                .salary("15,00,000 - 28,00,000")
                .type("FULL_TIME")
                .description("Data scientists analyze large amounts of data to find patterns and make predictions. They help companies make data-driven decisions. Skills: Python, statistics, machine learning, SQL, data visualization. Focus: Insights and predictive modeling.")
                .postedBy(smitha)
                .active(true)
                .build());

        Job job7 = jobRepository.save(Job.builder()
                .title("Machine Learning Engineer")
                .company("AI Nexus Labs")
                .location("Hyderabad, India")
                .salary("18,00,000 - 35,00,000")
                .type("FULL_TIME")
                .description("They build and deploy AI/ML models into real-world applications. For example: recommendation systems or chatbots. Skills: Python, TensorFlow/PyTorch, algorithms, ML theory. Focus: AI model development and deployment.")
                .postedBy(jaydeep)
                .active(true)
                .build());

        Job job8 = jobRepository.save(Job.builder()
                .title("QA Engineer / Software Tester")
                .company("QualityFirst Solutions")
                .location("Pune, India")
                .salary("5,00,000 - 12,00,000")
                .type("FULL_TIME")
                .description("QA engineers test software to find bugs before release. They ensure the product works correctly and meets requirements. Skills: Manual testing, automation tools (Selenium, Cypress), attention to detail. Focus: Quality and reliability.")
                .postedBy(jaydeep)
                .active(true)
                .build());

        Job job9 = jobRepository.save(Job.builder()
                .title("Mobile App Developer")
                .company("AppForge Technologies")
                .location("Chennai, India")
                .salary("8,00,000 - 16,00,000")
                .type("FULL_TIME")
                .description("Mobile developers build apps for Android and iOS devices. Examples include food delivery or banking apps. Skills: Kotlin/Java (Android), Swift (iOS), Flutter/React Native. Focus: Mobile application development.")
                .postedBy(smitha)
                .active(true)
                .build());

        Job job10 = jobRepository.save(Job.builder()
                .title("Cloud Engineer")
                .company("SkyScale Cloud Services")
                .location("Remote")
                .salary("16,00,000 - 30,00,000")
                .type("FULL_TIME")
                .description("Cloud engineers design and manage cloud infrastructure. They handle servers, storage, and scalability in cloud platforms. Skills: AWS/Azure/GCP, networking, virtualization, security basics. Focus: Scalable infrastructure and cloud systems.")
                .postedBy(smitha)
                .active(true)
                .build());

        // SAMPLE APPLICATIONS
        applicationRepository.save(Application.builder()
                .job(job1).candidate(omkar).status(Application.Status.SHORTLISTED)
                .coverLetter("I have 3 years of experience in Java and Spring Boot. Passionate about building scalable backend systems.")
                .build());

        applicationRepository.save(Application.builder()
                .job(job3).candidate(omkar).status(Application.Status.PENDING)
                .coverLetter("Skilled in backend development with Java and SQL. Would love to contribute to your team.")
                .build());

        applicationRepository.save(Application.builder()
                .job(job2).candidate(prajwal).status(Application.Status.REVIEWED)
                .coverLetter("Strong experience in React and modern JavaScript. I love creating beautiful and responsive UIs.")
                .build());

        applicationRepository.save(Application.builder()
                .job(job4).candidate(prajwal).status(Application.Status.PENDING)
                .coverLetter("As a developer skilled in both frontend and backend, I can deliver end-to-end solutions.")
                .build());

        applicationRepository.save(Application.builder()
                .job(job6).candidate(manik).status(Application.Status.ACCEPTED)
                .coverLetter("With 4 years in data science and ML, I can bring strong analytical skills to your data team.")
                .build());

        applicationRepository.save(Application.builder()
                .job(job7).candidate(manik).status(Application.Status.SHORTLISTED)
                .coverLetter("I have deployed multiple ML models in production using TensorFlow. Eager to work on AI solutions.")
                .build());

        System.out.println("  Users created:");
        System.out.println("     Admin:      admin@test.com / admin123");
        System.out.println("     Candidate:  omkar@test.com / pass123");
        System.out.println("     Candidate:  prajwal@test.com / pass123");
        System.out.println("     Candidate:  manik@test.com / pass123");
        System.out.println("     Recruiter:  jaydeep@test.com / pass123");
        System.out.println("     Recruiter:  smitha@test.com / pass123");
        System.out.println("  10 Jobs created, 6 Sample applications created");
        System.out.println("  === Ready! Open http://localhost:5000 ===\n");
    }
}
