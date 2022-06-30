import java.util.HashSet;
import java.util.Set;

public class CustomerService {

  private final Set<Customer> customers;
  private final Set<Appliance> appliances;

  public CustomerService() {
    this.customers = new HashSet<>();
    this.appliances = new HashSet<>();
  }

  public Customer getCustomer(String customerId) throws CustomerNotFoundException {
    for (Customer customer : customers) {
      if (customer.getId().equals(customerId)) {
        return customer;
      }
    }
    throw new CustomerNotFoundException(customerId);
  }

  public Customer saveCustomer(Customer customer) throws CustomerNotSavedException {
    try {
      return getCustomer(customer.getId());
    } catch (CustomerNotFoundException e) {
      if (customers.add(customer)) {
        return customer;
      } else {
        throw new CustomerNotSavedException();
      }
    }
  }

  public void updateApplianceConnectionStatus(String applianceId, boolean status) {
    Appliance appliance = null;
    for (Customer customer : customers) {
      try {
        appliance = customer.getAppliance(applianceId);
      } catch (ApplianceNotFoundException ignored) {

      }
    }
    if (appliance != null) {
      appliance.setStatus(status);
    }
  }

  public Customer saveAppliance() {
    return null;
  }

}
