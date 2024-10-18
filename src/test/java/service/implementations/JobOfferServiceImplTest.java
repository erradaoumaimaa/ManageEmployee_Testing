package service.implementations;

import org.junit.jupiter.api.Test;
import dao.interfaces.JobOfferDao;
import entities.JobOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
class JobOfferServiceImplTest {

    private JobOfferDao jobOfferDao;
    private JobOfferServiceImpl jobOfferService;

    @BeforeEach
    void setUp() {
        jobOfferDao = Mockito.mock(JobOfferDao.class);
        jobOfferService = new JobOfferServiceImpl(jobOfferDao);
    }

    @Test
    void createJobOffer_ValidData_ShouldSaveOffer() {
        JobOffer validJobOffer = new JobOffer(
                "Developer",
                "Job description",
                "Requirements",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(10),
                "Active"
        );

        jobOfferService.createJobOffer(validJobOffer);

        verify(jobOfferDao, times(1)).save(validJobOffer);
    }

    @Test
    void createJobOffer_InvalidPublicationDate_ShouldThrowException() {
        JobOffer invalidJobOffer = new JobOffer(
                "Developer",
                "Job description",
                "Requirements",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(10),
                "Active"
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jobOfferService.createJobOffer(invalidJobOffer);
        });

        assertEquals("La date de publication ne peut pas être dans le futur.", exception.getMessage());
    }

    @Test
    void createJobOffer_MissingTitle_ShouldThrowException() {
        JobOffer invalidJobOffer = new JobOffer(
                "", // Title manquant
                "Job description",
                "Requirements",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Active"
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jobOfferService.createJobOffer(invalidJobOffer);
        });

        assertEquals("Le titre est obligatoire.", exception.getMessage());
    }
    @Test
    void updateJobOfferStatus_ShouldUpdateStatusToExpired() {
        JobOffer activeJobOffer = new JobOffer(
                "Developer",
                "Job description",
                "Requirements",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(1),
                "Active"
        );

        when(jobOfferDao.findAll()).thenReturn(Arrays.asList(activeJobOffer));

        jobOfferService.updateJobOfferStatus();

        assertEquals("Expired", activeJobOffer.getStatus());
        verify(jobOfferDao, times(1)).update(activeJobOffer);
    }
}


