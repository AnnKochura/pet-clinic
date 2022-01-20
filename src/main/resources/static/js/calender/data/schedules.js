'use strict';

/*eslint-disable*/

var ScheduleList = [];

var SCHEDULE_CATEGORY = [
    'milestone',
    'task'
];

function ScheduleInfo() {
    this.id = null;
    this.calendarId = null;

    this.title = null;
    this.body = null;
    this.isAllday = false;
    this.start = null;
    this.end = null;
    this.category = '';
    this.dueDateClass = '';

    this.color = null;
    this.bgColor = null;
    this.dragBgColor = null;
    this.borderColor = null;
    this.customStyle = '';

    this.isFocused = false;
    this.isPending = false;
    this.isVisible = true;
    this.isReadOnly = false;
    this.goingDuration = 0;
    this.comingDuration = 0;
    this.recurrenceRule = '';
    this.state = '';

    this.raw = {
        memo: '',
        hasToOrCc: false,
        hasRecurrenceRule: false,
        location: null,
        class: 'public', // or 'private'
        creator: {
            name: '',
            avatar: '',
            company: '',
            email: '',
            phone: ''
        }
    };
}

function generateTime(schedule, renderStart, renderEnd) {

    console.log( JSON.stringify( schedule ) )

    var startDate = moment(renderStart.getTime())
    var endDate = moment(renderEnd.getTime());

    if (schedule.isAllday) {
        schedule.category = 'allday';
    } else {
        schedule.category = 'time';
    }

    schedule.start = startDate.toDate();
    schedule.end = endDate.toDate();

}

function generateNames() {
    var names = [];
    var i = 0;
    var length = chance.integer({min: 1, max: 10});

    for (; i < length; i += 1) {
        names.push(chance.name());
    }

    return names;
}


function generateRandomScheduleX( calendar, item ) {

    var schedule = new ScheduleInfo();

    schedule.id = item.id;
    schedule.calendarId = calendar.id;

    schedule.title = item.title;
    schedule.body = item.body;
    schedule.isReadOnly = item.isReadOnly;

    const startDate =  new Date( item.start )
    const endDate =  new Date( item.end );

    generateTime(schedule, startDate, endDate );

    schedule.location = item.location;
    schedule.attendees = [];
    schedule.recurrenceRule =  '';
    schedule.state = item.state;
    schedule.color = calendar.color;
    schedule.bgColor = calendar.bgColor;
    schedule.dragBgColor = calendar.dragBgColor;
    schedule.borderColor = calendar.borderColor;
    ScheduleList.push(schedule);
}

function generateSchedule(viewName, renderStart, renderEnd) {
    ScheduleList = [];
    CalendarList.forEach(function(calendar) {
        $.ajax({
            url: 'http://localhost:8090/restcalender/listSchedule/'+calendar.id,
            async: false,
            success: function (res) {
                console.log( JSON.stringify( res ) )
                res.listSchedule.forEach( (item, index) => {
                    generateRandomScheduleX(calendar, item);
                } )

            },
            error: function (err) {
                console.error(err)
            }
        })
    });
}
