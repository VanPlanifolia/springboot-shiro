package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 14431
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perms {
    Integer permid;
    Integer userid;
    String permMsg;
}
