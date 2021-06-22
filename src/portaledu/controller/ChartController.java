package portaledu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import portaledu.DAO.ExamDAO;
import portaledu.DAO.ProfessorDAO;
import portaledu.DAO.StudentDAO;
import portaledu.DAO.UserDAO;
import portaledu.model.ProfessorModel;
import portaledu.model.UserModel;

@RequestScoped
@ManagedBean(name = "chartBean")
public class ChartController {
	
	// private BarChartModel barExam;
	// private BarChartModel barStudent;
	private BarChartModel barProfessor;
	private BarChartModel barUser;
	
	/*
	@ManagedProperty(value="#{ExamDAO}")
	private ExamDAO eDAO;
	
	@ManagedProperty(value="#{StudentDAO}")
	private StudentDAO sDAO;
	*/
	
	private List<ProfessorModel> professors;
	private List<ProfessorModel> professorsActive;
	private List<ProfessorModel> professorsInactive;
	
	private List<UserModel> users;
	private List<UserModel> usersActive;
	private List<UserModel> usersInactive;
	private List<UserModel> usersBlocked;
	
	@ManagedProperty(value="#{ProfessorDAO}")
	
	private ProfessorDAO pDAO;
	
	@ManagedProperty(value="#{UserDAO}")
	private UserDAO uDAO;

	
	public List<ProfessorModel> getProfessors() {
		professors = pDAO.getAll();
		return professors;
	}

	public void setProfessors(List<ProfessorModel> professors) {
		this.professors = professors;
	}

	public List<ProfessorModel> getProfessorsActive() {
		professorsActive = pDAO.getActive();
		return professorsActive;
	}

	public void setProfessorsActive(List<ProfessorModel> professorsActive) {
		this.professorsActive = professorsActive;
	}

	public List<ProfessorModel> getProfessorsInactive() {
		professorsInactive = pDAO.getInactive();
		return professorsInactive;
	}

	public void setProfessorsInactive(List<ProfessorModel> professorsInactive) {
		this.professorsInactive = professorsInactive;
	}

	public List<UserModel> getUsers() {
		users = uDAO.getAll();
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public List<UserModel> getUsersActive() {
		usersActive = uDAO.getActive();
		return usersActive;
	}

	public void setUsersActive(List<UserModel> usersActive) {
		this.usersActive = usersActive;
	}

	public List<UserModel> getUsersInactive() {
		usersInactive = uDAO.getInactive();
		return usersInactive;
	}

	public void setUsersInactive(List<UserModel> usersInactive) {
		this.usersInactive = usersInactive;
	}

	public List<UserModel> getUsersBlocked() {
		usersBlocked = uDAO.getBlocked();
		return usersBlocked;
	}

	public void setUsersBlocked(List<UserModel> usersBlocked) {
		this.usersBlocked = usersBlocked;
	}

	public ProfessorDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(ProfessorDAO pDAO) {
		this.pDAO = pDAO;
	}

	public UserDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	public ChartController() throws Exception {
    	// createBarExam();
    	// createBarStudent();
    	createBarProfessor();
    	createBarUser();    	
    }
    
	public BarChartModel getBarUser() {
		return barUser;
	}

	public void setBarUser(BarChartModel barUser) {
		this.barUser = barUser;
	}
/*
	public BarChartModel getBarStudent() {
		return barStudent;
	}

	public void setBarStudent(BarChartModel barStudent) {
		this.barStudent = barStudent;
	}
*/
	public BarChartModel getBarProfessor() {
		return barProfessor;
	}

	public void setBarProfessor(BarChartModel barProfessor) {
		this.barProfessor = barProfessor;
	}
/*
	public BarChartModel getBarExam() {
		return barExam;
	}

	public void setBarExam(BarChartModel barExam) {
		this.barExam = barExam;
	}
*/
	public void createBarUser() throws Exception {
		
		barUser = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Usu�rios");

        List<Number> values = new ArrayList<>();
        values.add(this.users.size());
        values.add(this.usersInactive.size());
        values.add(this.usersActive.size());
        values.add(this.usersBlocked.size());
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Total");
        labels.add("Ativos");
        labels.add("Inativos");
        labels.add("Bloqueados");
        data.setLabels(labels);
        barUser.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barUser.setOptions(options);
        
    }
/*	
	public void createBarStudent() throws Exception {
		
		barStudent = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Alunos");

        List<Number> values = new ArrayList<>();
        values.add(sDAO.getAll().size());
        values.add(sDAO.getInactive().size());
        values.add(sDAO.getActive().size());
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(255, 99, 132)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Total");
        labels.add("Matriculados");
        labels.add("Inativos");
        data.setLabels(labels);
        barStudent.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barStudent.setOptions(options);
        
    }
*/
	
	public void createBarProfessor() throws Exception {
		
		barProfessor = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Professores");

        List<Number> values = new ArrayList<>();
        values.add(this.professors.size());
        values.add(this.professorsActive.size());
        values.add(this.professorsInactive.size());
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(255, 99, 132)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Total");
        labels.add("Ativos");
        labels.add("Inativos");
        data.setLabels(labels);
        barProfessor.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barProfessor.setOptions(options);
        
    }

/*
	public void createBarExam() throws Exception {
		
		barExam = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Provas");

        List<Number> values = new ArrayList<>();
        values.add(eDAO.getAll().size());
        values.add(eDAO.getInactive().size());
        values.add(eDAO.getActive().size());
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(255, 99, 132)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Total");
        labels.add("Aplicadas");
        labels.add("Canceladas");
        data.setLabels(labels);
        barExam.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barExam.setOptions(options);
        
    }
*/
	
}