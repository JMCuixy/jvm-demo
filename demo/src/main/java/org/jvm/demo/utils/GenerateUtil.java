package org.jvm.demo.utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/16.
 */
public class GenerateUtil {

    /**
     * ****************使用前必读*******************
     * *
     * * 使用前请将 moduleName 更改为自己模块的名称即可（一般情况下与数据库名一致），其他无须改动。
     * *
     * *********************************************
     */
    private final String type_char = "char";
    private final String type_date = "date";
    private final String type_timestamp = "timestamp";

    private final String type_int = "int";
    private final String type_double = "double";
    private final String type_bigint = "bigint";
    private final String type_text = "text";
    private final String type_bit = "bit";
    private final String type_decimal = "decimal";
    private final String type_blob = "blob";


    private final String bean_path = "d:/generate/";
    private final String mapper_path = "d:/generate/";
    private final String xml_path = "d:/generate/";
    private final String service_path = "d:/generate/";

    private final String bean_package = "com.yungu.swift.order";
    private final String service_package = "com.yungu.swift.order.service.model.dto";
    private final String mapper_package = "com.yungu.swift.order.dao";

    /**
     * 数据库信息
     */
    private final String driverName = "com.mysql.jdbc.Driver";
    private final String user = "xxx";
    private final String password = "xxx";
    private final String url = "jdbc:mysql://xxxxx/xxx?characterEncoding=utf8";
    /**
     * 数据库连接
     */
    private Connection conn = null;

    /**
     * 对应模块名称
     */
    private String moduleName = "";
    private String tableName = null;
    private String beanName = null;
    private String mapperName = null;
    private String serviceName = null;
    private String serviceImplName = null;


    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
    }


    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            tables.add(results.getString(1));
        }
        return tables;
    }


    private void processTable(String table) {
        StringBuilder sb = new StringBuilder(table.length());
        String[] tables = table.toLowerCase().split("_");
        String temp;
        for (int i = 1; i < tables.length; i++) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        moduleName = tables[0];
        if (tables.length > 1) {
            moduleName = tables[1];
        }
        moduleName = moduleName.toLowerCase();
        String name = sb.toString();
        beanName = name + "Dto";
        mapperName = name + "Mapper";
        serviceName = name + "Service";
        serviceImplName = name + "ServiceImpl";
    }


    private String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        }
        if (type.indexOf(type_bigint) > -1) {
            return "Long";
        }
        if (type.indexOf(type_int) > -1) {
            return "Integer";
        }
        if (type.indexOf(type_date) > -1) {
            return "java.util.Date";
        }
        if (type.indexOf(type_text) > -1) {
            return "String";
        }
        if (type.indexOf(type_timestamp) > -1) {
            return "java.util.Date";
        }
        if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        }
        if (type.indexOf(type_decimal) > -1) {
            return "java.math.BigDecimal";
        }
        if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        if (type.indexOf(type_double) > -1) {
            return "Double";
        }
        return "Object";
    }


    private String processField(String field) {
        StringBuilder sb = new StringBuilder(field.length());
        String[] fields = field.split("_");
        String temp;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb.toString();
    }


    /**
     * 将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    private String processResultMapId(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }


    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" **/");
        return bw;
    }


    /**
     * 构建方法上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }


    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        String path = bean_path + moduleName + "/dto/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File beanFile = new File(path, beanName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + "." + moduleName + ".dto;");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("@SuppressWarnings(\"serial\")");
        bw.newLine();
        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            bw.write("\t/**");
            bw.newLine();
            bw.write("\t*" + comments.get(i));
            bw.newLine();
            bw.write("\t*/");
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * 生成service类
     *
     * @throws IOException
     */
    private void buildService() throws IOException {
        String path = service_path + moduleName + "/service/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(path, serviceName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + service_package + "." + moduleName + ".service;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();
        bw.write("import com.yungu.swift.base.ResponseData;");
        bw.newLine();
        bw = buildClassComment(bw, serviceName + "");
        bw.newLine();
        bw.newLine();
        bw.write("public interface " + serviceName + "extends BaseService<" + beanName + "> {");
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 生成service类
     *
     * @throws IOException
     */
    private void buildServiceImpl() throws IOException {
        String path = service_path + moduleName + "/service/impl/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(path, serviceImplName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + service_package + "." + moduleName + ".service.impl;");

        bw.newLine();
        bw.write("import com.alibaba.dubbo.config.annotation.Service;");
        bw.newLine();
        bw.write("import org.springframework.beans.factory.annotation.Autowired;");
        bw.newLine();

        bw.write("import " + bean_package + "." + moduleName + ".services." + serviceName + ";");
        bw.newLine();
        bw.write("import " + bean_package + "." + moduleName + ".dto." + beanName + ";");
        bw.newLine();
        bw.write("import " + mapper_package + "." + moduleName + ".mapper." + mapperName + ";");
        bw.newLine();
        bw = buildClassComment(bw, serviceImplName + "");
        bw.newLine();
        bw.newLine();
        bw.write("@Service");
        bw.newLine();
        bw.write("public class " + serviceImplName + "extends BaseServiceImpl<" + beanName + "> implements " + serviceName + "{");
        bw.newLine();
        bw.write("\t@Autowired");
        bw.newLine();
        bw.write("\tprivate " + mapperName + "<" + beanName + "> " + processResultMapId(mapperName) + ";");
        bw.newLine();

        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tprotected IMapper<" + beanName + "> getMapper() {");
        bw.newLine();
        bw.write("\t\treturn " + mapperName + ";");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * 构建Mapper文件
     *
     * @throws IOException
     */
    private void buildMapper() throws IOException {
        String path = mapper_path + moduleName + "/mapper/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(path, mapperName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + "." + moduleName + ".mapper;");
        bw.newLine();
        bw.newLine();
        bw.write("import " + bean_package + "." + moduleName + ".dto." + beanName + ";");
        bw.newLine();
        bw.write("import com.base.IMapper;");
        bw = buildClassComment(bw, mapperName + "数据库操作接口类");
        bw.newLine();
        bw.newLine();
        bw.write("public interface " + mapperName + "<T extends " + beanName + "> extends IMapper<T> {");

        bw.write("}");
        bw.flush();
        bw.close();
    }


    /**
     * 构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {
        String path = xml_path + moduleName + "/mapper/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(path, mapperName + ".xml");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + mapper_package + "." + moduleName + ".mapper." + mapperName + "\">");
        bw.newLine();
        bw.newLine();

        buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }


    private void buildSQL(BufferedWriter bw, List<String> columns, List<String> types) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();

        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"add\" parameterType=\"" + processResultMapId(beanName) + "\" keyColumn=\"" + columns.get(0) + "\" useGeneratedKeys=\"true\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(" \t\t\t(");
        bw.newLine();
        for (int i = 0; i < size; i++) {
            bw.write(" \t\t\t");
            bw.write(" " + columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
            bw.newLine();

        }
        bw.write(" \t\t\t) ");
        bw.newLine();
        bw.write("\t\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t\t(");
        bw.newLine();
        for (int i = 0; i < size; i++) {
            bw.write(" \t\t\t");
            bw.write(" #{" + processField(columns.get(i)) + "}");
            if (i != size - 1) {
                bw.write(",");
            }
            bw.newLine();
        }
        bw.write(" \t\t\t) ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完

        String tempField = null;

        // ----- 条件更新 （匹配有值的字段）
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"edit\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.write(columns.get(i) + " = #{" + tempField + "},");
            bw.write("</if>");
            bw.newLine();
        }

        bw.write("\t\t<where>");
        bw.newLine();

        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.write("\tand\t" + columns.get(i) + " = #{" + tempField + "}");
            bw.write("</if>");
            bw.newLine();
        }
        bw.write("\t\t</where>");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();

        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write("\t<delete id=\"del\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完


        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selInfo\" resultType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"sqlColumns\"/>");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完

        //
        bw.write("\t<!-- 条件-->");
        bw.newLine();
        bw.write("\t<sql id=\"sqlWhere\">");
        bw.newLine();

        bw.write("\t\t<where>");
        bw.newLine();

        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.write("\tand\t" + columns.get(i) + " = #{" + tempField + "}");
            bw.write("</if>");
            bw.newLine();
        }
        bw.write("\t\t</where>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();

        //
        bw.write("\t<!-- 字段 -->");
        bw.newLine();
        bw.write("\t<sql id=\"sqlColumns\">");
        bw.newLine();
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t");
            bw.write(columns.get(i));
            bw.write("\t");
            bw.write(tempField);
            if (i < size - 1) {
                bw.write(",");
            }
            bw.newLine();
        }
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();

        bw.write("\t<select id=\"count\" resultType=\"int\">");
        bw.newLine();
        bw.write("\t\tselect");
        bw.newLine();
        bw.write("\t\t\tcount(" + columns.get(0) + ")");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t<include refid=\"sqlWhere\"/>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();

        bw.write("\t<select id=\"list\" resultType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\tselect");
        bw.newLine();
        bw.write("\t\t\t <include refid=\"sqlColumns\"/>");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t<include refid=\"sqlWhere\"/>");
        bw.newLine();
        bw.write("\t\torder by create_time desc");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();


        bw.write("\t<select id=\"queryPage\" resultType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\tselect");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"sqlColumns\"/>");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t<include refid=\"sqlWhere\"/>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
    }


    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment(String table) throws SQLException {
        Map<String, String> maps = new HashMap<>();
        String tbsql = "show table status";
        if (!StringUtils.isEmpty(table)) {
            tbsql = tbsql + " where name='" + table + "'";
        }
        PreparedStatement pstate = conn.prepareStatement(tbsql);
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }


    public void generate() throws ClassNotFoundException, SQLException, IOException {
        init();
        List<String> columns;
        List<String> types;
        List<String> comments;
        String prefix = "show full fields from ";
        PreparedStatement pstate;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment(null);
        for (String table : tables) {
            columns = new ArrayList<>();
            types = new ArrayList<>();
            comments = new ArrayList<>();
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                String field = results.getString("FIELD");
                columns.add(field);
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTable(table);
            String tableComment = tableComments.get(tableName);
            buildEntityBean(columns, types, comments, tableComment);
            buildMapper();
            buildMapperXml(columns, types, comments);
            buildService();
            buildServiceImpl();
        }
        conn.close();
    }

    /***
     * 单表生成
     * @param table
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void generateOne(String table) throws ClassNotFoundException, SQLException, IOException {
        init();
        List<String> columns = new ArrayList<>();
        List<String> types = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        String prefix = "show full fields from ";
        PreparedStatement pstate = conn.prepareStatement(prefix + table);
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String field = results.getString("FIELD");
            columns.add(field);
            types.add(results.getString("TYPE"));
            comments.add(results.getString("COMMENT"));
        }
        Map<String, String> tableComments = getTableComment(table);
        tableName = table;
        processTable(table);
        String tableComment = tableComments.get(tableName);
        buildEntityBean(columns, types, comments, tableComment);
        buildMapper();
        buildMapperXml(columns, types, comments);
        buildService();
        buildServiceImpl();
        conn.close();
    }


    public static void main(String[] args) {
        try {
            //单表生成
            new GenerateUtil().generateOne("t_order_secret_no_report");
            // 自动打开生成文件的目录
            Runtime.getRuntime().exec("cmd /c start explorer D:\\");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
