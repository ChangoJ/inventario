let grades = [
    {
        subject: "Matematicas",
        grade: 9.5
    },
    {
        subject: "Fisica",
        grade: 9.2
    }
]

export const saveGrade = (grade) => {
    grades.push(grade);
}

export const getGrades = () => {
    return grades;
}

export const updateGrade = (nota) => {
    let gradeRetrieved = find(nota.subject)
    if (gradeRetrieved != null) {
        gradeRetrieved.grade = nota.grade
    }
    
}

export const find = (subject) => {

    for (let i = 0; i < grades.length; i++) {
       
        if(grades[i].subject == subject){
            return grades[i]
        }
       
        
    }

    return null
}