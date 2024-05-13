
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { GradeForm } from "./app/screens/GradeForm"
import { ListGrades } from "./app/screens/ListGrades"
import "react-native-gesture-handler";
import { createDrawerNavigator } from '@react-navigation/drawer';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { ContenidoA } from './app/screens/ContenidoA';
import { ContenidoB } from './app/screens/ContenidoB';

export default function App() {

  const StackGrades = createNativeStackNavigator();
  const Drawer = createDrawerNavigator()
  const Tab = createBottomTabNavigator()

  const TabNav = () => {
    return (
    



        <Tab.Navigator>
          <Tab.Screen
            name="ContenidoANav"
            component={ContenidoA}
            options={{
              headerShown: false,
              tabBarLabel: "Configuracion"
            }}
          />
          <Tab.Screen
            name="ContenidoBNav"
            component={ContenidoB}
            options={{
              headerShown: false,
              tabBarLabel: "Acerca De"
            }}
          />
        </Tab.Navigator>
    )
  }

  const StackNav = () => {
      <StackGrades.Navigator>
        <StackGrades.Screen name="ListGradesNav" component={ListGrades} />
        <StackGrades.Screen name="GradeFormNav" component={GradeForm} />
      </StackGrades.Navigator>
  }

  

  return (
    <NavigationContainer>


      <Drawer.Navigator>
        <Drawer.Screen
          name="ListGradesNav"
          component={ListGrades}
          options={{
            title: "Lista de Notas"
          }}
        />

        <Drawer.Screen
          name="TabNav"
          component={TabNav}
          options={{
            title: "Añadir TabNav"
          }}
        />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}

