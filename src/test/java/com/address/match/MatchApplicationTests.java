package com.address.match;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * 单元测试
 */
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
//@RunWith(SpringRunner.class)
@SpringBootTest
class MatchApplicationTests {

    private String snippetDir = "target/generated-snippets";
    private String outputDir = "target/asciidoc";
//    @Autowired
//    private MockMvc mockMvc;
//
//    @After
//    public void Test() throws Exception{
//        // 得到swagger.json,写入outputDir目录中
//        mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
//                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
//        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
//        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
//                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
//                .withExamples(snippetDir)
//                .build()
//                .intoFolder(outputDir);// 输出
//    }
//
//    @Test
//    public void TestApi() throws Exception{
//        mockMvc.perform(get("/student").param("name", "xxx")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("getStudent", preprocessResponse(prettyPrint())));
//
//        Student student = new Student();
//        student.setName("xxx");
//        student.setAge(23);
//        student.setAddress("湖北麻城");
//        student.setCls("二年级");
//        student.setSex("男");
//
//        mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(student))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andDo(document("addStudent", preprocessResponse(prettyPrint())));
//    }

}
