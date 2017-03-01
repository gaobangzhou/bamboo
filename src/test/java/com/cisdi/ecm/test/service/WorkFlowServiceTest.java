package com.cisdi.ecm.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class WorkFlowServiceTest extends TestSupport {

	@Resource
	private ProcessEngine processEngine;

	@Test
	public void test_sys() {
		System.out.println("-----");
	}

	@Test
	public void testProcess() throws FileNotFoundException {
		// InputStream inputStream = new
		// FileInputStream("F:\\javaProgram\\workflow\\MyProcess.zip");
		//
		InputStream inputStream = this.getClass().getResourceAsStream("/diagrams/MyProcess.zip");
		 ZipInputStream zInputStream = new ZipInputStream(inputStream);
		 Deployment deployment = processEngine.getRepositoryService()
		 .createDeployment().name("myProcess")
		 .addZipInputStream(zInputStream).deploy();
		 System.out.println("部署的id :"+deployment.getId());
		 System.out.println("部署的name :"+deployment.getName());
	}

}
