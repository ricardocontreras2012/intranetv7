/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.wrapper;

import infrastructure.dto.CursoInsJsonDTO;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class CursoResponseWrapper {
    private String status;
    private List<CursoInsJsonDTO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CursoInsJsonDTO> getData() {
        return data;
    }

    public void setData(List<CursoInsJsonDTO> data) {
        this.data = data;
    }
}

