package Louay.schoolManagement.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor

public class Errors {

    private String errorMessage;
    private List<String> errorDetails;
}
