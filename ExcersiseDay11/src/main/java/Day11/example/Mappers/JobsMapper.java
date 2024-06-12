package Day11.example.Mappers;


import Day11.example.DTO.JobsDTO;
import Day11.example.Models.Jobs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobsMapper {

    JobsMapper INSTANCE = Mappers.getMapper(JobsMapper.class);

    JobsDTO toJobsDto(Jobs j);


    Jobs toModel(JobsDTO dto);

}
