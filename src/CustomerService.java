import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CustomerService {

  private final Set<Customer> customers;
  private final Set<Appliance> appliances;

  public CustomerService() {
    this.customers = new HashSet<>();
    this.appliances = new HashSet<>();
  }

  public Optional<Customer> getCustomer(String customerId) {
    for (Customer customer : customers) {
      if (customer.getId().equals(customerId)) {
        return Optional.of(customer);
      }
    }
    return Optional.empty();
  }

  public Customer saveCustomer(Customer customer) {
    Optional<Customer> foundCustomer = getCustomer(customer.getId());
    if (foundCustomer.isPresent()) {
      return foundCustomer.get();
    } else {
      customers.add(customer);
      return customer;
    }
  }

  public void updateApplianceConnectionStatus(String applianceId, boolean status)
      throws ApplianceNotFoundException {
    Optional<Appliance> foundAppliance = getAppliance(applianceId);
    if (foundAppliance.isPresent()) {
      foundAppliance.get().setStatus(status);
    } else {
      throw new ApplianceNotFoundException(applianceId);
    }
  }

  private Optional<Appliance> getAppliance(String applianceId) {
    for (Appliance oldAppliance : appliances) {
      if (oldAppliance.getId().equals(applianceId)) {
        return Optional.of(oldAppliance);
      }
    }
    return Optional.empty();
  }

  public void saveAppliance(String customerId, Appliance appliance)
      throws CustomerNotFoundException {
    Optional<Customer> optionalCustomer = getCustomer(customerId);
    if (optionalCustomer.isPresent()) {
      optionalCustomer.get().upsertAppliance(appliance);

      Optional<Appliance> optionalAppliance = getAppliance(appliance.getId());
      if (optionalAppliance.isPresent()) {
        optionalAppliance.get().setStatus(appliance.isStatus());
      } else {
        appliances.add(appliance);
      }
    } else {
      throw new CustomerNotFoundException(customerId);
    }
  }

}
