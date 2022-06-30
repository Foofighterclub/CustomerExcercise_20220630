
import java.util.HashSet;
import java.util.Set;

public class Customer {

  private final String id;
  private final String taxCode;
  private final Set<Appliance> appliances;

  public Customer(String id, String taxCode) {
    this.id = id;
    this.taxCode = taxCode;
    this.appliances = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public String getTaxCode() {
    return taxCode;
  }

  public Appliance getAppliance(String applianceId) throws ApplianceNotFoundException {
    for (Appliance appliance : appliances) {
      if (appliance.getId().equals(applianceId)) {
        return appliance;
      }
    }
    throw new ApplianceNotFoundException(this.id, applianceId);
  }

  public void setAppliance(Appliance appliance) throws ApplianceNotSavedException {
    try {
      Appliance oldAppliance = getAppliance(appliance.getId());
      oldAppliance.setStatus(appliance.isStatus());
    } catch (ApplianceNotFoundException e) {
      if (!appliances.add(appliance)) {
        throw new ApplianceNotSavedException();
      }
    }
  }

}
