package org.poseidon.pojo;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.poseidon.util.DateUtil;

@Entity
@Table(name = "t_download_file", catalog = "poseidon")
public class DownloadFile implements java.io.Serializable {

    private static final long serialVersionUID = 4857779815842875346L;

    public static final String FILE_STATUS_PENDING = "P";
    public static final String FILE_STATUS_SUCCESS = "S";
    public static final String FILE_STATUS_FAIL = "F";
    
    private Long id;
    private String fileStatus;
    private String filePath;
    private Date startTime;
    private Date finishTime;
    private String interval;

    // Constructors

    /** default constructor */
    public DownloadFile() {
    }

    /** full constructor */
    public DownloadFile(String fileStatus, String filePath, Date startTime, Date finishTime) {
        this.fileStatus = fileStatus;
        this.filePath = filePath;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "file_status", length = 1)
    public String getFileStatus() {
        return this.fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Column(name = "file_path", length = 50)
    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Column(name = "start_time", length = 19)
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "finish_time", length = 19)
    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Transient
    public String getInterval() {
        return DateUtil.getBetweenStr(this.startTime, this.finishTime);
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}