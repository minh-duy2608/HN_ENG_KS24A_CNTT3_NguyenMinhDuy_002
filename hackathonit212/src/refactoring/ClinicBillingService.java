import java.util.*;
interface InsuranceStrategy{double apply(double a);}
class BHYTInsurance implements InsuranceStrategy{public double apply(double a){return a*0.2;}}
class BaoVietInsurance implements InsuranceStrategy{public double apply(double a){return a-500000;}}
interface PaymentStrategy{void pay();}
class VNPayPayment implements PaymentStrategy{public void pay(){System.out.println("Routing VNPay");}}
class CashPayment implements PaymentStrategy{public void pay(){System.out.println("Print receipt");}}
interface NotificationService{void send(String phone);}
class SmsNotificationService implements NotificationService{public void send(String p){System.out.println("SMS "+p);}}
class Patient{boolean active=true;String phone="";boolean isActive(){return active;}String getPhone(){return phone;}}
class ServiceItem{double fee;double getFee(){return fee;}}
class VisitRecord{double baseFee;List<ServiceItem> extraServices=new ArrayList<>();double getBaseFee(){return baseFee;}List<ServiceItem> getExtraServices(){return extraServices;}}
class Invoice{Patient p;double total;String status;Invoice(Patient p,double t,String s){this.p=p;this.total=t;this.status=s;}}
public class ClinicBillingService{
 private final Map<String,InsuranceStrategy> ins=Map.of("BHYT",new BHYTInsurance(),"BaoViet",new BaoVietInsurance());
 private final Map<String,PaymentStrategy> pay=Map.of("VNPay",new VNPayPayment(),"Cash",new CashPayment());
 private final NotificationService notify=new SmsNotificationService();
 public Invoice calculateAndPay(Patient p,VisitRecord v,String insurance,String payment){
   if(!p.isActive()) throw new RuntimeException("Patient locked");
   double total=v.getBaseFee();
   for(ServiceItem i:v.getExtraServices()) total+=i.getFee();
   if(insurance!=null&&ins.containsKey(insurance)) total=ins.get(insurance).apply(total);
   if(!pay.containsKey(payment)) throw new RuntimeException("Payment not supported");
   pay.get(payment).pay();
   Invoice inv=new Invoice(p,total,"PAID");
   notify.send(p.getPhone());
   return inv;
 }
}