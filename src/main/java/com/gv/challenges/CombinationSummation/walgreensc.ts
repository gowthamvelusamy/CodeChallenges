class CustomerInformation{
    constructor(
        public customerId: string,
        public rxDetailsList: Array<RxDetails>
    ) { }
}
class RxDetails{
    constructor(
        public customerId: string,
        public rxId:string
    ) { }
}

var prescribedCustomers = new Array<CustomerInformation>();

var patient: CustomerInformation;
var rxArray = new Array<RxDetails>();
var rx: RxDetails;

rx = new RxDetails("coldPatientId", "headAcheRxId");
rxArray.push(rx);
rx = new RxDetails("coldPatientId", "bodyPainRxId");
rxArray.push(rx);
patient = new CustomerInformation("coldPatientId", rxArray);

prescribedCustomers.push(patient)

rxArray = new Array;
rx = new RxDetails("coronaPatientId", "chestPainRxId");
rxArray.push(rx);
rx = new RxDetails("coronaPatientId", "bodyPainRxId");
rxArray.push(rx);
patient = new CustomerInformation("coronaPatientId", rxArray);
prescribedCustomers.push(patient)

rxArray = new Array;
rx = new RxDetails("accidentPatientId", "bodyPainRxId");
patient = new CustomerInformation("accidentPatientId", rxArray);
prescribedCustomers.push(patient)

let prescribedCustomersMap: Map<string, Set<string>>;
prescribedCustomersMap = new Map<string, Set<string>>();

prescribedCustomers.forEach(prescribedCustomer => {
    var prescribedRxArray = prescribedCustomer.rxDetailsList;
    let prescriptions: Set<string> = new Set;
    prescribedRxArray.forEach(prescribedRx => {
        prescriptions.add(prescribedRx.rxId);
    });
    prescribedCustomersMap.set(prescribedCustomer.customerId, prescriptions);
});

var storeCustomers = new Array<CustomerInformation>();
rxArray = new Array;
rx = new RxDetails("coldPatientId", "headAcheRxId");
rxArray.push(rx);
rx = new RxDetails("coldPatientId", "bodyPainRxId");
rxArray.push(rx);
rx = new RxDetails("coldPatientId", "earPainRxId");
rxArray.push(rx);
patient = new CustomerInformation("coldPatientId", rxArray);

storeCustomers.push(patient)

rxArray = new Array;
rx = new RxDetails("coronaPatientId", "throatPainRxId");
rxArray.push(rx);
rx = new RxDetails("coronaPatientId", "chestPainRxId");
rxArray.push(rx);
rx = new RxDetails("coronaPatientId", "bodyPainRxId");
rxArray.push(rx);
patient = new CustomerInformation("coronaPatientId", rxArray);
storeCustomers.push(patient)


rxArray = new Array;
rx = new RxDetails("fluPatientId", "throatPainRxId");
rxArray.push(rx);
rx = new RxDetails("fluPatientId", "chestPainRxId");
rxArray.push(rx);
rx = new RxDetails("fluPatientId", "bodyPainRxId");
rxArray.push(rx);
patient = new CustomerInformation("fluPatientId", rxArray);
storeCustomers.push(patient)

rxArray = new Array;
rx = new RxDetails("accidentPatientId", "bodyPainRxId");
patient = new CustomerInformation("accidentPatientId", rxArray);
storeCustomers.push(patient)

console.log("prescriptionBasedCustomers list");
console.log(prescribedCustomers);

console.log("storebasedCustomers list");
console.log(storeCustomers)

console.log("trying to filter");
var filteredStoreCustomers = new Array<CustomerInformation>();


storeCustomers.forEach(storeCustomer => {
    if (prescribedCustomersMap.has(storeCustomer.customerId)) {
        rxArray = new Array;
        storeCustomer.rxDetailsList.forEach(storeCustomersRx => {
            if (! prescribedCustomersMap.get(storeCustomersRx.customerId).has(storeCustomersRx.rxId)) {
                rxArray.push(storeCustomersRx);
            }
        });
        if (rxArray.length > 0) {
            patient = new CustomerInformation(storeCustomer.customerId, rxArray);
            filteredStoreCustomers.push(patient);
        }
    }
});
console.log("filtered store customer details");
console.log(filteredStoreCustomers);