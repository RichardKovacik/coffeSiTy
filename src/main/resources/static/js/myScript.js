// $("districts").change(
//     function() {
//         $.getJSON("http://localhost:8080/regions", {
//             districtId : $(this).val(),
//             ajax : 'true'
//         }, function(data) {
//             var html = '<option value="">--alege agentia--</option>';
//             var len = data.length;
//             for ( var i = 0; i < len; i++) {
//                 html += '<option value="' + data[i].name + '">'
//                     + data[i].name + '</option>';
//             }
//             html += '</option>';
//             $('regions').html(html);
//         });
//     });
// function myFunction(event) {
//     return event;
// }
// console.log(myFunction())
