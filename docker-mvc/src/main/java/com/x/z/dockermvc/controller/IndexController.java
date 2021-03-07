package com.x.z.dockermvc.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class IndexController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @GetMapping("/ds/index")
    public List<String> index() throws SQLException {
        Connection connection = DriverManager.getConnection(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()){
            String user = resultSet.getString("user");
            list.add(user);
        }
        log.info("result: {}", JSON.toJSONString(list));
        log.debug("result: {}", JSON.toJSONString(list));
        log.warn("result: {}", JSON.toJSONString(list));
        log.trace("result: {}", JSON.toJSONString(list));
        return list;
    }
}
