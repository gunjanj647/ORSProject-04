package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.ResultBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.ResultModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;


@WebServlet(name="ResultCtl", urlPatterns={"/ctl/ResultCtl"})
public class ResultCtl extends BaseCtl {


    @Override
    protected void preload(HttpServletRequest request) {

        StudentModel studentModel = new StudentModel();

        try {

            List studentList = studentModel.list();

            request.setAttribute("studentList", studentList);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;


        if(DataValidator.isNull(request.getParameter("resultId"))) {

            request.setAttribute("resultId",
                    PropertyReader.getValue(
                    "error.require","Result ID"));

            pass = false;
        }


        if(DataValidator.isNull(request.getParameter("studentId"))) {

            request.setAttribute("studentId",
                    PropertyReader.getValue(
                    "error.require","Student"));

            pass = false;
        }


        if(DataValidator.isNull(request.getParameter("percentage"))) {

            request.setAttribute("percentage",
                    PropertyReader.getValue(
                    "error.require","Percentage"));

            pass = false;
        }


        if(DataValidator.isNull(request.getParameter("grade"))) {

            request.setAttribute("grade",
                    PropertyReader.getValue(
                    "error.require","Grade"));

            pass = false;
        }


        if(DataValidator.isNull(request.getParameter("resultStatus"))) {

            request.setAttribute("resultStatus",
                    PropertyReader.getValue(
                    "error.require","Result Status"));

            pass = false;
        }


        return pass;
    }




    @Override
    protected BaseBean populateBean(HttpServletRequest request) {


        ResultBean bean = new ResultBean();


        bean.setId(
        DataUtility.getLong(request.getParameter("id")));


        bean.setResultId(
        DataUtility.getString(request.getParameter("resultId")));


        bean.setStudentId(
        DataUtility.getLong(request.getParameter("studentId")));


        bean.setPercentage(
        DataUtility.getInt(request.getParameter("percentage")));


        bean.setGrade(
        DataUtility.getString(request.getParameter("grade")));


        bean.setResultStatus(
        DataUtility.getString(request.getParameter("resultStatus")));



        populateDTO(bean, request);


        return bean;
    }





    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        long id = DataUtility.getLong(
                request.getParameter("id"));


        ResultModel model = new ResultModel();


        if(id > 0) {

            try {

                ResultBean bean = model.findByPk(id);

                ServletUtility.setBean(bean, request);


            } catch(Exception e) {

                e.printStackTrace();

                ServletUtility.handleException(
                e, request, response);

                return;
            }
        }


        ServletUtility.forward(
        getView(), request, response);

    }





    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String op =
        DataUtility.getString(
        request.getParameter("operation"));



        ResultModel model = new ResultModel();


        long id =
        DataUtility.getLong(
        request.getParameter("id"));



        if(OP_SAVE.equalsIgnoreCase(op)) {


            ResultBean bean =
            (ResultBean)populateBean(request);


            try {


                long pk = model.add(bean);


                ServletUtility.setBean(bean, request);


                ServletUtility.setSuccessMessage(
                "Result added successfully", request);



            } catch(DuplicateRecordException e) {


                ServletUtility.setBean(bean, request);


                ServletUtility.setErrorMessage(
                "Result already exists", request);



            } catch(ApplicationException e) {


                e.printStackTrace();

                ServletUtility.handleException(
                e, request, response);

                return;
            }



        } else if(OP_RESET.equalsIgnoreCase(op)) {


            ServletUtility.redirect(
            ORSView.RESULT_CTL,
            request,
            response);

            return;



        } else if(OP_UPDATE.equalsIgnoreCase(op)) {


            ResultBean bean =
            (ResultBean)populateBean(request);



            try {


                if(id > 0) {

                    model.update(bean);
                }


                ServletUtility.setBean(bean, request);


                ServletUtility.setSuccessMessage(
                "Result updated successfully",
                request);



            }  catch (DuplicateRecordException e) {


                ServletUtility.setBean(bean, request);


                ServletUtility.setErrorMessage(
                "Result already exists",
                request);



            } catch(ApplicationException e) {


                e.printStackTrace();


                ServletUtility.handleException(
                e, request, response);

                return;
            }
        }



        ServletUtility.forward(
        getView(), request, response);

    }




    @Override
    protected String getView() {

        return ORSView.RESULT_VIEW;
    }

}