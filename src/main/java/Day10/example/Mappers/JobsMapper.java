package Day10.example.Mappers;


import Day10.example.DTO.JobsDTO;
import Day10.example.Models.Jobs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobsMapper {

    JobsMapper INSTANCE = Mappers.getMapper(JobsMapper.class);

    JobsDTO toDeptDto(Jobs j);


    Jobs toModel(JobsDTO dto);

}
