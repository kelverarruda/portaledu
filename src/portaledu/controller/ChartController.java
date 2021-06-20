package portaledu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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

import portaledu.DAO.ExamDAOImpl;
import portaledu.DAO.ProfessorDAOImpl;
import portaledu.DAO.StudentDAOImpl;
import portaledu.DAO.UserDAOImpl;
import portaledu.model.ExamModel;
import portaledu.model.ProfessorModel;
import portaledu.model.StudentModel;
import portaledu.model.UserModel;

@RequestScoped
@ManagedBean(name = "chartBean")
public class ChartController {
	
	private BarChartModel barUser;
	private BarChartModel barStudent;
	private BarChartModel barProfessor;
	private BarChartModel barExam;
	
	private ExamDAOImpl eDAO;
	private StudentDAOImpl sDAO;
	private ProfessorDAOImpl pDAO;
	private UserDAOImpl uDAO;
	

    @PostConstruct
    public void init() throws Exception {
    	createBarUser();
    	createBarProfessor();
    	createBarStudent();
    	createBarExam();
    }
    
	public BarChartModel getBarUser() {
		return barUser;
	}

	public void setBarUser(BarChartModel barUser) {
		this.barUser = barUser;
	}

	public BarChartModel getBarStudent() {
		return barStudent;
	}

	public void setBarStudent(BarChartModel barStudent) {
		this.barStudent = barStudent;
	}

	public BarChartModel getBarProfessor() {
		return barProfessor;
	}

	public void setBarProfessor(BarChartModel barProfessor) {
		this.barProfessor = barProfessor;
	}

	public BarChartModel getBarExam() {
		return barExam;
	}

	public void setBarExam(BarChartModel barExam) {
		this.barExam = barExam;
	}

	public void createBarUser() throws Exception {
		
		uDAO = new UserDAOImpl();
		barUser = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Usu�rios");

        List<Number> values = new ArrayList<>();
        values.add(uDAO.getAll(UserModel.class).size());
        values.add(uDAO.getInactive(UserModel.class).size());
        values.add(uDAO.getActive(UserModel.class).size());
        values.add(uDAO.getBlocked(UserModel.class).size());
        
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
	
	public void createBarStudent() throws Exception {
		
		sDAO = new StudentDAOImpl();
		barStudent = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Alunos");

        List<Number> values = new ArrayList<>();
        values.add(sDAO.getAll(StudentModel.class).size());
        values.add(sDAO.getInactive(StudentModel.class).size());
        values.add(sDAO.getActive(StudentModel.class).size());
        
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
	
	public void createBarProfessor() throws Exception {
		
		pDAO = new ProfessorDAOImpl();
		barProfessor = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Professores");

        List<Number> values = new ArrayList<>();
        values.add(pDAO.getAll(ProfessorModel.class).size());
        values.add(pDAO.getInactive(ProfessorModel.class).size());
        values.add(pDAO.getActive(ProfessorModel.class).size());
        
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
	
	public void createBarExam() throws Exception {
		
		eDAO = new ExamDAOImpl();
		barExam = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Provas");

        List<Number> values = new ArrayList<>();
        values.add(eDAO.getAll(ExamModel.class).size());
        values.add(eDAO.getInactive(ExamModel.class).size());
        values.add(eDAO.getActive(ExamModel.class).size());
        
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
    
}