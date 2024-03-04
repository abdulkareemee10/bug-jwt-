package com.ktl.shipokauserservice.employeeSize;

import com.ktl.shipokauserservice.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSizeService {

    @Autowired
    private EmployeeSizeRepository employeeSizeRepository;

    public Response saveEmployeeSize(EmployeeSizeRequest employeeSizeRequest){
        Response response = new Response();

    try{
        EmployeeSize employeeSize = new EmployeeSize();
        employeeSize.setEmployeeSize(employeeSizeRequest.getEmployeeSize());

        employeeSizeRepository.save(employeeSize);
        if ( employeeSizeRepository.save(employeeSize) != null) {
        response.setResponseCode(0);
        response.setResponseMessage("employee size successfully saved.");
    } else {
            response.setResponseCode(-1);
            response.setResponseMessage("Problem saving employee size.");
        }
    } catch (Exception e) {
        response.setResponseCode(-2);
        response.setResponseMessage(e.getMessage());
    }
        return response;
}

    public ResponseEntity<List<EmployeeSize>> readEmployeeType(){
        try{
            List<EmployeeSize> employeeSizes = employeeSizeRepository.findAll() ;
            return new ResponseEntity<>(employeeSizes, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }

    }

    public Response updateEmployeeSize(EmployeeSizeRequest employeeSize, Long id) {
        Response response = new Response();
        try {
            EmployeeSize existingEmployeeSize = employeeSizeRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("Employee Size Not Found"));

            existingEmployeeSize.setEmployeeSize(employeeSize.getEmployeeSize());


            employeeSizeRepository.save(existingEmployeeSize);
            if (employeeSizeRepository.save(existingEmployeeSize) != null){
                response.setResponseCode(0);
            response.setResponseMessage("employee size updated successfully");
        } else {
            response.setResponseCode(-1);
            response.setResponseMessage("Problem updating employee size. ");
        }

    } catch (Exception e) {
        response.setResponseCode(-2);
        response.setResponseMessage(e.getMessage());
    }
        return response;
}

    public Response deleteEmployeeById(Long id) {
        Response response = new Response();

       try{ EmployeeSize employeeSize = employeeSizeRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("employee size Not Found"));
        employeeSizeRepository.deleteById(id);
        response.setResponseCode(0);
        response.setResponseMessage("employee size deleted successfully");

    } catch (Exception e) {
        response.setResponseCode(-2);
        response.setResponseMessage(e.getMessage());
    }
        return response;
}
}
