package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    11/12/13 09:58
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Hello {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement  stmt = null;
        ResultSet  rset = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@(description=(address=(host=labm3amdb04.devlab.ad)(protocol=tcp)(port=1521))(connect_data=(sid=amqa)))", "bsm_923lin_rtsm", "topaz");
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT table_name  FROM all_tables where table_name in ('CCM_CLASSES', 'DBTABLE_1', 'SQLPERFORMANCEMONITOR_1', 'DB_REDOFILEGROUP_1', 'DBAOBJECTS_1', 'DB_ARCHIVEFILE_1', 'DBTABLESPACE_1', 'DBSEGMENT_1', 'DBLINKOBJ_1', 'DB_REDOFILE_1', 'SQLJOB_1', 'DBEXTENT_1', 'DBJOB_1', 'DATABASE_INSTANCE_1', 'SQLFILE_1', 'DBSCHEDULERJOB_1', 'DBSNAPSHOT_1', 'SQLBACKUP_1', 'DB_CONTROLFILE_1', 'DBINDEX_1', 'DBTNS_1', 'DBUSER_1', 'SQLALERT_1', 'SQLJOBSTEP_1', 'MAINFRAME_IMSDBAREA_1', 'SQLSERVERRESOURCE_1', 'DB_TRACE_FILE_1', 'DB_LOG_FILE_1', 'CDM_DATABASERESOURCE_1' )");
            while (rset.next()) {
                System.out.println(rset.getString(1));
            }

        }
        finally {
            if (rset!=null) rset.close();
            if (stmt!=null) stmt.close();
            if (conn!=null) conn.close();
        }
    }
}
