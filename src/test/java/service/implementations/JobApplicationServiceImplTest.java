package service.implementations;

import dao.interfaces.JobApplicationDao;
import dao.interfaces.JobOfferDao;
import entities.JobApplication;
import entities.JobOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobApplicationServiceImplTest {

    @Mock
    private JobApplicationDao jobApplicationDao;

    @Mock
    private JobOfferDao jobOfferDao;

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyForJob_WithValidJobOffer_ShouldSaveApplication() {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1L);
        JobApplication jobApplication = new JobApplication("John Doe", "john@example.com", "123456789", "CV.pdf", jobOffer, "Pending");

        when(jobOfferDao.findById(1L)).thenReturn(Optional.of(jobOffer));

        jobApplicationService.applyForJob(jobApplication);


        verify(jobApplicationDao, times(1)).save(jobApplication);
    }

    @Test
    void applyForJob_WithInvalidJobOffer_ShouldThrowException() {

        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(2L);
        JobApplication jobApplication = new JobApplication("John Doe", "john@example.com", "123456789", "CV.pdf", jobOffer, "Pending");

        when(jobOfferDao.findById(2L)).thenReturn(Optional.empty());


        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobApplicationService.applyForJob(jobApplication));
        assertEquals("Job offer not found.", exception.getMessage());

        verify(jobApplicationDao, never()).save(jobApplication);
    }

    @Test
    void acceptApplication_WithValidApplicationId_ShouldUpdateStatusToAccepted() {

        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(1L);
        jobApplication.setStatus("Pending");

        when(jobApplicationDao.findById(1L)).thenReturn(Optional.of(jobApplication));


        jobApplicationService.acceptApplication(1L);


        assertEquals("Accepted", jobApplication.getStatus());
        verify(jobApplicationDao, times(1)).update(jobApplication);
    }

    @Test
    void acceptApplication_WithInvalidApplicationId_ShouldThrowException() {
        when(jobApplicationDao.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobApplicationService.acceptApplication(1L));
        assertEquals("Application not found.", exception.getMessage());

        verify(jobApplicationDao, never()).update(any(JobApplication.class));
    }

    @Test
    void rejectApplication_WithValidApplicationId_ShouldUpdateStatusToRejected() {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(1L);
        jobApplication.setStatus("Pending");

        when(jobApplicationDao.findById(1L)).thenReturn(Optional.of(jobApplication));

        jobApplicationService.rejectApplication(1L);

        assertEquals("Rejected", jobApplication.getStatus());
        verify(jobApplicationDao, times(1)).update(jobApplication);
    }

    @Test
    void rejectApplication_WithInvalidApplicationId_ShouldThrowException() {
        // Arrange
        when(jobApplicationDao.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobApplicationService.rejectApplication(1L));
        assertEquals("Application not found.", exception.getMessage());

        verify(jobApplicationDao, never()).update(any(JobApplication.class));
    }
}
