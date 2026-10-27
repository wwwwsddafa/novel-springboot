$(function () {
    loadCommentReport();
});

function loadCommentReport() {
    $('#exampleTable').bootstrapTable('destroy').bootstrapTable({
        url: "/novel/commentReport/list",
        striped: true,
        sidePagination: "server",
        pagination: true,
        singleSelect: true,
        idField: "id",
        sortOrder: "desc",
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        queryParams: function (params) {
            var temp = {
                limit: params.limit,
                offset: params.offset,
                sort: params.sort,
                order: params.order,
                status: $('#status').val(),
                reportType: $('#reportType').val()
            };
            return temp;
        },
        responseHandler: function (res) {
            return {
                "total": res.data.total,
                "rows": res.data.rows
            };
        },
        columns: [
            {
                checkbox: true
            },
            {
                field: 'id',
                title: '编号'
            },
            {
                field: 'targetContent',
                title: '被举报内容',
                formatter: function (value) {
                    if (value && value.length > 50) {
                        return '<span title="' + value + '">' + value.substring(0, 50) + '...</span>';
                    }
                    return value;
                }
            },
            {
                field: 'targetType',
                title: '内容类型',
                formatter: function (value) {
                    if (value == 1) return '<span class="label label-primary">评论</span>';
                    if (value == 2) return '<span class="label label-info">回复</span>';
                    return value;
                }
            },
            {
                field: 'reporterName',
                title: '举报人'
            },
            {
                field: 'commenterName',
                title: '被举报人'
            },
            {
                field: 'reportType',
                title: '举报类型',
                formatter: function (value) {
                    if (value == 'spam') return '垃圾广告';
                    if (value == 'abuse') return '侮辱谩骂';
                    if (value == 'porn') return '色情低俗';
                    if (value == 'other') return '其他';
                    return value;
                }
            },
            {
                field: 'reportReason',
                title: '举报描述',
                formatter: function (value) {
                    if (value && value.length > 30) {
                        return '<span title="' + value + '">' + value.substring(0, 30) + '...</span>';
                    }
                    return value || '-';
                }
            },
            {
                field: 'status',
                title: '状态',
                formatter: function (value) {
                    if (value == 0) return '<span class="label label-warning">待处理</span>';
                    if (value == 1) return '<span class="label label-default">已忽略</span>';
                    if (value == 2) return '<span class="label label-danger">已删除</span>';
                    return value;
                }
            },
            {
                field: 'handlerNote',
                title: '处理备注',
                formatter: function (value) {
                    return value || '-';
                }
            },
            {
                field: 'createTime',
                title: '举报时间'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row) {
                    var html = '';
                    if (row.status == 0) {
                        html += '<a class="btn btn-primary btn-sm" href="#" onclick="handleReport(\'' + value + '\',1)" title="忽略"><i class="fa fa-close"></i></a> ';
                        html += '<a class="btn btn-danger btn-sm" href="#" onclick="handleReport(\'' + value + '\',2)" title="删除内容"><i class="fa fa-trash"></i></a> ';
                    }
                    html += '<a class="btn btn-warning btn-sm" href="#" onclick="removeReport(\'' + value + '\')" title="删除记录"><i class="fa fa-times-circle"></i></a>';
                    return html;
                }
            }
        ]
    });
}

function reLoad() {
    loadCommentReport();
}

function handleReport(id, status) {
    var title = status == 1 ? '忽略该举报？' : '确认删除该评论内容？';
    var msg = status == 1 ? '请输入忽略原因（选填）：' : '请输入处理备注（选填）：';
    layer.prompt({title: title, formType: 2}, function (text, index) {
        layer.close(index);
        $.ajax({
            type: "post",
            url: "/novel/commentReport/handle",
            data: {id: id, status: status, handlerNote: text},
            success: function (r) {
                if (r.code == 0) {
                    layer.msg('操作成功');
                    reLoad();
                } else {
                    layer.alert(r.msg);
                }
            }
        });
    });
}

function removeReport(id) {
    layer.confirm('确认要删除该举报记录吗？', function (index) {
        $.ajax({
            type: "post",
            url: "/novel/commentReport/remove",
            data: {id: id},
            success: function (r) {
                if (r.code == 0) {
                    layer.msg('删除成功');
                    reLoad();
                } else {
                    layer.alert(r.msg);
                }
            }
        });
    });
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        layer.msg("请选择要删除的记录");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条举报记录吗？", function (index) {
        var ids = new Array();
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: "post",
            url: "/novel/commentReport/batchRemove",
            data: {"ids": ids},
            success: function (r) {
                if (r.code == 0) {
                    layer.msg('删除成功');
                    reLoad();
                } else {
                    layer.alert(r.msg);
                }
            }
        });
    });
}