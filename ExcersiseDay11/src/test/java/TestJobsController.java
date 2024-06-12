
import Day11.example.DTO.JobFilterDto;
import jakarta.ws.rs.core.*;
import Day11.example.Controller.JobsController;
import Day11.example.DAO.JobsDAO;
import Day11.example.DTO.JobsDTO;
import Day11.example.Mappers.JobsMapper;
import Day11.example.Models.Jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestJobsController {

    @InjectMocks
    JobsController josCont;

    @Mock
//    @Spy
    JobsDAO dao;

    @Mock
    JobsMapper mapper;

    @Mock
    UriInfo uriInfo;

    @Mock
    HttpHeaders headers;

    @Test
    public void TestJobsController() throws SQLException, ClassNotFoundException {
        Jobs j = new Jobs(50000.0,1500.0,"Manager",1);
        JobsDTO jDto = new JobsDTO(50000.0,1500.0,"Manager",1);
        URI uri = URI.create("http://localhost/api/department/1");
        JobFilterDto filterDto = new JobFilterDto();
        ArrayList<MediaType> medias = new ArrayList<>();
        medias.add(MediaType.valueOf(MediaType.APPLICATION_JSON));

        when(dao.selectJob(1)).thenReturn(j);
        when(mapper.toJobsDto(j)).thenReturn(jDto);
        when(uriInfo.getAbsolutePath()).thenReturn(uri);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(UriBuilder.fromUri(uri));
        when(headers.getAcceptableMediaTypes()).thenReturn(medias);


        Assertions.assertDoesNotThrow(() -> josCont.SELECT_ALL_JOBS(filterDto));

        jDto.getLinks().clear();
        Response res = josCont.SELECT_ALL_JOBS(filterDto);

        verify(mapper, times(2)).toJobsDto(j);

        Assertions.assertEquals(200, res.getStatus());
        Assertions.assertNotNull(((JobsDTO) res.getEntity()).getLinks());
        Assertions.assertEquals(2, ((JobsDTO) res.getEntity()).getLinks().size());
    }

}
