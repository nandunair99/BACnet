import org.code_house.bacnet4j.wrapper.api.BacNetClient;
import org.code_house.bacnet4j.wrapper.api.BacNetObject;
import org.code_house.bacnet4j.wrapper.api.BacNetToJavaConverter;
import org.code_house.bacnet4j.wrapper.api.Device;
import org.code_house.bacnet4j.wrapper.ip.BacNetIpClient;

import java.util.List;
import java.util.Set;

public class MainClass {
    public static void main(String[] args) {
        try {
            BacNetClient client = new BacNetIpClient("2.234.210.62", 47808);
            client.start();
            Set<Device> devices = client.discoverDevices(20000); // given number is timeout in millis
            for (Device device : devices) {
                System.out.println(device + "=" + device.getName());
//                System.out.println("InstanceNumber:" + device.getInstanceNumber());
//                System.out.println("ModelName:" + device.getModelName());
//                System.out.println("NetworkNumber:" + device.getNetworkNumber());
//                System.out.println("ObjectIdentifier:" + device.getObjectIdentifier());
//                System.out.println("BacNet4jAddress:" + device.getBacNet4jAddress().getDescription());
                
                System.out.println("================================================");
                try {
                    if (device.getName().equalsIgnoreCase("Indoor Unit")) {
                        List<BacNetObject> bacNetObjectList = client.getDeviceObjects(device);

                        for (BacNetObject bacNetObject : bacNetObjectList) {
                            System.out.println(bacNetObject.getType());
                            System.out.println("properties: "+bacNetObject.getName() + "=" + bacNetObject.getDescription() + "=" + bacNetObject.getUnits());

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            client.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
