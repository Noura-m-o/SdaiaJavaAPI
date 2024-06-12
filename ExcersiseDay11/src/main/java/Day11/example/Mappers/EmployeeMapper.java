package Day11.example.Mappers;


import Day11.example.DTO.EmployeeDTO;
import Day11.example.Models.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO toDeptDto(Employees employees);


    Employees toModel(EmployeeDTO dto);

}
