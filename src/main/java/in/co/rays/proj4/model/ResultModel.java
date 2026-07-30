package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.ResultBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;


public class ResultModel {


    public static Long nextPk() {

        long pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getconnection();

            PreparedStatement pstmt =
                    conn.prepareStatement("select max(id) from st_result");

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                pk = rs.getLong(1);
            }

            rs.close();
            pstmt.close();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return pk + 1;
    }



    public static ResultBean findByPk(long id) throws Exception {

        Connection conn = null;
        ResultBean bean = null;

        try {

            conn = JDBCDataSource.getconnection();

            PreparedStatement pst =
                    conn.prepareStatement("select * from st_result where id=?");

            pst.setLong(1,id);

            ResultSet rs = pst.executeQuery();


            while(rs.next()) {

                bean = new ResultBean();

                bean.setId(rs.getLong(1));
                bean.setResultId(rs.getString(2));
                bean.setStudentId(rs.getLong(3));
                bean.setPercentage(rs.getDouble(4));
                bean.setGrade(rs.getString(5));
                bean.setResultStatus(rs.getString(6));

                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDateTime(rs.getTimestamp(9));
                bean.setModifiedDateTime(rs.getTimestamp(10));
            }

            rs.close();
            pst.close();

        }catch(Exception e) {

            e.printStackTrace();

        }finally {

            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }



    public static Long add(ResultBean bean)
            throws ApplicationException, DuplicateRecordException {


        long pk = nextPk();
        Connection conn = null;


        try {

            conn = JDBCDataSource.getconnection();
            conn.setAutoCommit(false);


            PreparedStatement pst =
                    conn.prepareStatement(
                    "insert into st_result values(?,?,?,?,?,?,?,?,?,?)");


            pst.setLong(1,pk);
            pst.setString(2,bean.getResultId());
            pst.setLong(3,bean.getStudentId());
            pst.setDouble(4,bean.getPercentage());
            pst.setString(5,bean.getGrade());
            pst.setString(6,bean.getResultStatus());

            pst.setString(7,bean.getCreatedBy());
            pst.setString(8,bean.getModifiedBy());

            pst.setTimestamp(9,bean.getCreatedDateTime());
            pst.setTimestamp(10,bean.getModifiedDateTime());


            pst.executeUpdate();

            conn.commit();

            pst.close();


        }catch(Exception e){

            try {
                conn.rollback();
            }catch(Exception ex){

                throw new ApplicationException(
                "Rollback exception "+ex.getMessage());
            }

            throw new ApplicationException(
            "Exception in adding Result");

        }finally {

            JDBCDataSource.closeConnection(conn);
        }


        return pk;
    }




   
   	public static void update(ResultBean bean) throws DuplicateRecordException,ApplicationException { 
    

        Connection conn=null;


        try {

            conn=JDBCDataSource.getconnection();

            conn.setAutoCommit(false);


            PreparedStatement pst =
                    conn.prepareStatement(
                    "update st_result set result_id=?,student_id=?,percentage=?,grade=?,result_status=?,modified_by=?,modified_datetime=? where id=?");


            pst.setString(1,bean.getResultId());
            pst.setLong(2,bean.getStudentId());
            pst.setDouble(3,bean.getPercentage());
            pst.setString(4,bean.getGrade());
            pst.setString(5,bean.getResultStatus());

            pst.setString(6,bean.getModifiedBy());
            pst.setTimestamp(7,
            new Timestamp(System.currentTimeMillis()));

            pst.setLong(8,bean.getId());


            pst.executeUpdate();

            conn.commit();

            pst.close();


        }catch(Exception e){

            try {
                conn.rollback();
            }catch(Exception ex){

                throw new ApplicationException(
                "Rollback exception "+ex.getMessage());
            }

            throw new ApplicationException(
            "Exception in updating Result");


        }finally {

            JDBCDataSource.closeConnection(conn);
        }

    }




    public void delete(ResultBean bean)
            throws ApplicationException {


        Connection conn=null;


        try {

            conn=JDBCDataSource.getconnection();

            conn.setAutoCommit(false);


            PreparedStatement pst =
                    conn.prepareStatement(
                    "delete from st_result where id=?");


            pst.setLong(1,bean.getId());

            pst.executeUpdate();

            conn.commit();

            pst.close();


        }catch(Exception e){

            try {
                conn.rollback();
            }catch(Exception ex){

                throw new ApplicationException(
                "Rollback exception "+ex.getMessage());
            }

            throw new ApplicationException(
            "Exception in delete Result");


        }finally {

            JDBCDataSource.closeConnection(conn);
        }

    }




    public static List search(ResultBean bean,
            int pageNo,int pageSize)
            throws ApplicationException {


        Connection conn=null;

        List list=new ArrayList();


        try {

            conn=JDBCDataSource.getconnection();


            StringBuffer sql =
            new StringBuffer("select * from st_result where 1=1");


            if(bean!=null) {

                if(bean.getResultId()!=null &&
                bean.getResultId().length()>0) {

                    sql.append(
                    " and result_id like '"+bean.getResultId()+"%'");
                }


                if(bean.getStudentId()>0) {

                    sql.append(
                    " and student_id="+bean.getStudentId());
                }


                if(bean.getGrade()!=null &&
                bean.getGrade().length()>0) {

                    sql.append(
                    " and grade='"+bean.getGrade()+"'");
                }


                if(bean.getResultStatus()!=null &&
                bean.getResultStatus().length()>0) {

                    sql.append(
                    " and result_status='"+bean.getResultStatus()+"'");
                }
            }



            if(pageNo>0 && pageSize>0) {

                pageNo=(pageNo-1)*pageSize;

                sql.append(
                " limit "+pageNo+","+pageSize);
            }



            PreparedStatement pst =
                    conn.prepareStatement(sql.toString());


            ResultSet rs=pst.executeQuery();



            while(rs.next()) {


                ResultBean rbean=new ResultBean();


                rbean.setId(rs.getLong(1));
                rbean.setResultId(rs.getString(2));
                rbean.setStudentId(rs.getLong(3));
                rbean.setPercentage(rs.getDouble(4));
                rbean.setGrade(rs.getString(5));
                rbean.setResultStatus(rs.getString(6));


                list.add(rbean);
            }


            rs.close();
            pst.close();


        }catch(Exception e){

            throw new ApplicationException(
            "Exception in search Result");

        }finally {

            JDBCDataSource.closeConnection(conn);
        }


        return list;
    }



    public List<ResultBean> list()
            throws ApplicationException {

        return search(null,0,0);
    }

}