import { Input, Button } from "@rneui/base";
import { useState } from "react";
import { View, Text, StyleSheet, Alert } from "react-native"
import { saveGrade, updateGrade } from "../services/GradeServices"
import { ListGrades } from "./ListGrades";
export const GradeForm = ({ navigation, route }) => {

    let isNew = true
    let subjectR
    let gradeR

    if (route.params.notita != null) {
        isNew = false
    }

    if (!isNew) {
        subjectR = route.params.notita.subject
        gradeR = route.params.notita.grade
    }

    const [subject, setSubject] = useState(subjectR)
    const [grade, setGrade] = useState(gradeR == null ? null : gradeR + "")
    const [errorSubject, setErrorSubject] = useState()
    const [errorGrade, setErrorGrade] = useState()
    let hasErrors = false



    const save = () => {
        console.log(subject)
        setErrorSubject(null)
        setErrorGrade(null)
        validate()
        if (!hasErrors) {
            if(isNew){
                saveGrade({
                    subject: subject,
                    grade: grade
                })
            }else{
                updateGrade({
                    subject: subject,
                    grade: grade
                })
            }
            navigation.goBack()
            route.params.fnRefresh()
        }


    }

    const validate = () => {
        if (subject == "" || subject == null) {
            setErrorSubject("Debe ingresar una materia")
            hasErrors = true
        }

        let gradeFloat = parseFloat(grade)

        if (grade == null || isNaN(gradeFloat) || (gradeFloat > 10 || gradeFloat < 0)) {
            setErrorGrade("Debe ingresar una nota entre 0 y 10")
            hasErrors = true

        }
    }

    return <View style={styles.container}>
        <Text>FORMULARIO DE NOTAS</Text>

        <Input
            value={subject}
            onChangeText={setSubject}
            placeholder="Ejemplo: Matematicas"
            label="Materia"
            errorMessage={errorSubject}
            disabled={!isNew}
        />

        <Input
            value={grade}
            onChangeText={setGrade}
            placeholder="0-10"
            label="Nota"
            errorMessage={errorGrade}
        />
        <Button
            title="Guardar"
            icon={{
                name: "save",
                type: "entypo",
                color: "white"
            }}
            buttonStyle={styles.saveButton}
            onPress={save}
        />

    </View>
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    saveButton: {
        backgroundColor: "green"
    }
});