function getAllStudent(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/students/lists",
        success: function (data){
            showAllStudent(data)
        }
    })
    event.preventDefault();
}
function showAllStudent(lists){
    let res = "<tr>\n" +
        "    <td>STT</td>\n" +
        "    <td>Name</td>\n" +
        "    <td>Date Of Birth</td>\n" +
        "    <td>Email</td>\n" +
        "    <td>Address</td>\n" +
        "    <td colspan='2'>Action</td>\n" +
        "</tr>\n";
    for (let i = 0; i < lists.length; i++) {
        let student = lists[i];
        res += "<tr>\n" +
            "    <td>" + (i+1)       +   "</td>\n" +
            "    <td>" + student.name  + "</td>\n" +
            "    <td>" + student.dateOfBirth  + "</td>\n" +
            "    <td>" + student.email  + "</td>\n" +
            "    <td>" + student.address  + "</td>\n" +
            `<td><a class="deleteStudent" onclick='deleteStudentById($(this))' href="${student.id}">Delete</a></td>\n` +
            `<td><button class="EditStudent" onclick='showFormEdit(${student.id})'>Edit</button></td>\n` +
            `</tr>` ;
    }
    document.getElementById("display").innerHTML = `<table>` + res + `</table>`;
}
function deleteStudentById(a){
    let studentId = a.attr("href");
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/students/${studentId}`,
        success: function (data) {
            a.parent().parent().remove();
            location.reload();
            getAllStudent();
        },
        error: function (){
            alert("Sai cai gi do roi");
        }

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}
function showFormEdit(studentId){
    let form = "<input type=\"text\" id=\"name\" placeholder=\"Ho va ten\">\n" +
        "<br>" +
        "<input type=\"date\" id=\"dateOfBirth\" placeholder=\"Sinh nhat\">\n" +
        "<br>" +
        "<input type=\"text\" id=\"address\" placeholder=\"Dia chi\">\n" +
        "<br>" +
        "<input type=\"text\" id=\"phoneNumber\" placeholder=\"So dien thoai\">\n" +
        "<br>" +
        "<input type=\"email\" id=\"email\" placeholder=\"Email\">\n" +
        "<br>" +
        "<p>Chon lop hoc:</p>" +
        "<div id=\"classroom\"></div>" +
        "<button onclick=\"editStudent()\">Save</button>" +
        "<br>" +
        "<input type=\"hidden\" id=\"studentId\" value=\"" + studentId + "\">\n"
         selectClassroom();
    document.getElementById("display").innerHTML = form;
}
function selectClassroom(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/classroom/lists",
        success: function (data){
            document.getElementById("classroom").innerHTML = createSelectClassroom(data);
        }
    })
}
function createSelectClassroom(data){
    let checkbox = "";
    for (let i = 0; i < data.length; i++) {
        let name = data[i].name;
        checkbox += "<option value=\""+(i+1)+"\">"+ name +"</option>";
    }
    checkbox = "<select id=\"classroomId\">" + checkbox + "</select>";
    return checkbox;
}
function editStudent(){
    let name = document.getElementById("name").value;
    let dateOfBirth = document.getElementById("dateOfBirth").value;
    let address = document.getElementById("address").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let email = document.getElementById("email").value;
    let classroomId = document.getElementById("classroomId").value;
    let id = document.getElementById("studentId").value;
    let nal = {
        name: name,
        dateOfBirth: dateOfBirth,
        address: address,
        phoneNumber: phoneNumber,
        email: email,
        classroomId: classroomId
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        type: "PUT",
        url: "http://localhost:8080/students/" +id,
        data: JSON.stringify(nal),
        success: function (){
            alert("done");
            location.reload();
        },
        error: function (err){
            console.log(err)
        }
    })
}
function showFormAddStudent(){
    let form = "<input type=\"text\" id=\"name\" placeholder=\"Ho va ten\">\n" +
        "<br>" +
        "<input type=\"date\" id=\"dateOfBirth\" placeholder=\"Sinh nhat\">\n" +
        "<br>" +
        "<input type=\"text\" id=\"address\" placeholder=\"Dia chi\">\n" +
        "<br>" +
        "<input type=\"text\" id=\"phoneNumber\" placeholder=\"So dien thoai\">\n" +
        "<br>" +
        "<input type=\"email\" id=\"email\" placeholder=\"Email\">\n" +
        "<br>" +
        "<p>Chon lop hoc:</p>" +
        "<div id=\"classroom\"></div>" +
        "<button onclick=\"saveStudent()\">Save</button>" +
        selectClassroom();
    document.getElementById("display").innerHTML = form;
}
function saveStudent(){
    let name = document.getElementById("name").value;
    let dateOfBirth = document.getElementById("dateOfBirth").value;
    let address = document.getElementById("address").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let email = document.getElementById("email").value;
    let classroomId = document.getElementById("classroomId").value;

    let nal = {
        name: name,
        dateOfBirth: dateOfBirth,
        address: address,
        phoneNumber: phoneNumber,
        email: email,
        classroomId: classroomId
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        type: "POST",
        url: "http://localhost:8080/students/save",
        data: JSON.stringify(nal),
        success: function (){
            alert("done");
            location.reload();
        },
        error: function (err){
            console.log(err)
        }
    })
}
function searchStudent(){
    let name = document.getElementById("search").value;
    console.log(name)
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/students/"+name,
        success: function (data){
            showAllStudent(data)
        }
    })
    event.preventDefault();
}