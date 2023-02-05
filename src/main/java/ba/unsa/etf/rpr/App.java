package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeesManager;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.business.Receipt_TotalManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import static javafx.application.Application.launch;

/**
 * Hello world!
 *
 */
public class App {

    private static final Option addEmployee = new Option("e","add-employee",false, "Adding new employee to cash-register database");
    private static final Option addProduct = new Option("p","add-product",false, "Adding new product to cash-register database");
    private static final Option getProducts = new Option("getP", "get-products",false, "Printing all categories from cash-register database");
    private static final Option getReceipts = new Option("getR", "get-receipts",false, "Printing all receipts from cash-register database");
    private static final Option deleteProduct = new Option("dp", "delete-product",false, "Deleting product from table");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar CashRegister-cli-jar-with-dependencies.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addEmployee);
        options.addOption(addProduct);
        options.addOption(getProducts);
        options.addOption(getReceipts);
        options.addOption(deleteProduct);
        return options;
    }
    public static void main( String[] args ) throws Exception
    {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

//        while(true) {
        if((cl.hasOption(addEmployee.getOpt()) || cl.hasOption(addEmployee.getLongOpt()))){
            EmployeesManager employeeManager = new EmployeesManager();
            try{
            employeeManager.add(new Employees(1, cl.getArgList().get(0), cl.getArgList().get(1), cl.getArgList().get(2), false));
                System.out.println("Employee successfully added");
            }
            catch(Exception e) {
                System.out.println("Problem with adding to db");
            }
        }
        else if((cl.hasOption(addProduct.getOpt()) || cl.hasOption(addProduct.getLongOpt()))){
            ProductsManager productManager = new ProductsManager();
            try {
                productManager.add(new Products(1, cl.getArgList().get(0), Double.valueOf(cl.getArgList().get(1)), Integer.parseInt(cl.getArgList().get(2))));
                System.out.println("Product successfully added");
            }
            catch(Exception e){
                System.out.println("Problem with adding to db");
        }
        }
         else if(cl.hasOption(getProducts.getOpt()) || cl.hasOption(getProducts.getLongOpt())){
            ProductsManager productsManager = new ProductsManager();
            productsManager.getAll().forEach(c -> System.out.println(c.getName()));
//                break;
        } else if(cl.hasOption(getReceipts.getOpt()) || cl.hasOption(getReceipts.getLongOpt())) {
           Receipt_TotalManager rManager = new Receipt_TotalManager();
            rManager.getAll().forEach(c -> System.out.println(c.getTotal()+" - "+c.getDate()));
         }
         else if(cl.hasOption(deleteProduct.getOpt()) || cl.hasOption(deleteProduct.getLongOpt())){
            ProductsManager productManager = new ProductsManager();
            productManager.delete(Integer.parseInt(cl.getArgList().get(0)));
            System.out.println("Product deleted");
        }

         else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }
//        }
    }

    }


