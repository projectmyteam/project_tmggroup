$(document).ready(function () {
    $('#myTable').DataTable({
        columnDefs: [
            {
                targets: -1,
                className: 'dt-body-right'
            }
        ],
    });
});


