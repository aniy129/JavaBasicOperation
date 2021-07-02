/*!
 * Brav1Toolbox.js - common utility scripts and polyfills
 * http://marcolago.com/
 * MIT licensed
 *
 * Copyright (C) 2012-2013 Marco Lago, http://marcolago.com
 */
var Brav1Toolbox = (function() {
    var d = ["", "-webkit-", "-moz-", "-ms-", "-o-"];
    var i;
    if (window.getComputedStyle) {
        i = window.getComputedStyle(document.body)
    } else {
        i = document.documentElement.style
    }
    function k(m, o, n, l) {
        if (m.addEventListener) {
            m.addEventListener(o, n, l)
        } else {
            if (m.attachEvent) {
                m.attachEvent(o, n)
            }
        }
    }
    function g(l) {
        return j(l) != ""
    }
    function j(o) {
        for (var l = 0; l < d.length; l++) {
            var n = d[l].replace(/-/g, "");
            var m = o;
            if (n.length > 0) {
                m = m.charAt(0).toUpperCase() + m.substr(1)
            }
            m = n + m;
            if (m in i == true) {
                return m
            }
        }
        return ""
    }
    function e(l) {
        return !!l && Object.prototype.toString.call(l).match(/(\w+)\]/)[1]
    }
    function h(m, n) {
        if (m.classList) {
            m.classList.add(n)
        } else {
            if (f(m, n) == false) {
                var l = m.className;
                if (l.length > 0) {
                    l += " "
                }
                m.className = l + n
            }
        }
    }
    function a(m, n) {
        if (m.classList) {
            m.classList.remove(n)
        } else {
            var l = m.className;
            if (l.indexOf(n) != -1) {
                if (l.indexOf(" " + n) != -1) {
                    l = l.replace(" " + n, "")
                } else {
                    if (l.indexOf(n + " ") != -1) {
                        l = l.replace(n + " ", "")
                    } else {
                        l = l.replace(n, "")
                    }
                }
            }
            m.className = l
        }
    }
    function f(l, m) {
        if (l) {
            if (l.classList) {
                return l.classList.contains(m)
            } else {
                if (l.className) {
                    return l.className.indexOf(m) != -1
                }
            }
        }
        return false
    }
    function c(l, o) {
        if (document.createEvent) {
            var n = document.createEvent("HTMLEvents");
            n.initEvent(l, true, true);
            for (var m in o) {
                n[m] = o[m]
            }
            document.dispatchEvent(n)
        }
    }
    function b(m, l) {
        var o;
        var n;
        o = l.x - m.x;
        o *= o;
        n = l.y - m.y;
        n *= n;
        return Math.abs(Math.sqrt(o + n))
    }
    return {
        addListener: k,
        dispatchEvent: c,
        testCSS: g,
        getPrefixed: j,
        typeOf: e,
        addClass: h,
        removeClass: a,
        hasClass: f,
        distance: b
    }
}
)();
/*!
 * Flowtime.js
 * http://marcolago.com/flowtime-js/
 * MIT licensed
 *
 * Copyright (C) 2012-2013 Marco Lago, http://marcolago.com
 */
var Flowtime = (function() {
    var l = "ontouchstart"in document.documentElement;
    var aJ = window.history.pushState;
    var j = "ft-section";
    var aU = "." + j;
    var aM = "ft-page";
    var aK = "." + aM;
    var aG = "ft-fragment";
    var A = "." + aG;
    var ah = "revealed";
    var aI = "actual";
    var ax = "revealed-temp";
    var ak = "ft-default-progress";
    var N = "." + ak;
    var ab = "ft-section-thumb";
    var G = "." + ab;
    var k = "ft-page-thumb";
    var S = "." + k;
    var s = "flowtimenavigation";
    var J = document.querySelector(".flowtime");
    var aL = document.querySelector("html");
    var u = document.querySelector("body");
    var aA = false;
    var ac = "";
    var D = {
        section: 0,
        page: 0
    };
    var H = false;
    var aF = document.title;
    var O;
    var aN = 22;
    var az = null;
    var x = false;
    var p = true;
    var C = false;
    var M = false;
    var av = false;
    var aX = false;
    var aE = false;
    var ao = false;
    var h = 50;
    var g = 50;
    var T = document.querySelector(".parallax") != null;
    var I = true;
    try {
        var m = document.querySelector("html").className.toLowerCase();
        if (m.indexOf("ie7") != -1 || m.indexOf("ie8") != -1 || m.indexOf("lt-ie9") != -1) {
            I = false
        }
    } catch (aa) {
        I = false
    }
    if (I) {
        Brav1Toolbox.addClass(u, "ft-absolute-nav")
    }
    var z = (function() {
        var bN;
        var bm;
        var bw;
        var bJ;
        var bs;
        var bC = [];
        var bt = [];
        var bB = 0;
        var bZ = 0;
        var bK = 0;
        var bT = 0;
        var bq = 0;
        var bU = 0;
        var bj = 0;
        var bF;
        function ba() {
            bm = [];
            bt = [];
            bJ = document.querySelectorAll(A);
            bs = [];
            bN = J.querySelectorAll(".flowtime > " + aU);
            bw = J.querySelectorAll(".flowtime " + aK);
            for (var b2 = 0; b2 < bN.length; b2++) {
                var b0 = [];
                var b6 = bN[b2];
                bs[b2] = [];
                bC[b2] = [];
                if (b6.getAttribute("data-id")) {
                    b6.setAttribute("data-id", "__" + F(b6.getAttribute("data-id")))
                }
                b6.setAttribute("data-prog", "__" + (b2 + 1));
                b6.index = b2;
                b6.setAttribute("id", "");
                pages = b6.querySelectorAll(aK);
                bK += pages.length;
                bZ = Math.max(bZ, pages.length);
                for (var b3 = 0; b3 < pages.length; b3++) {
                    var b1 = pages[b3];
                    if (b1.getAttribute("data-id")) {
                        b1.setAttribute("data-id", "__" + F(b1.getAttribute("data-id")))
                    }
                    b1.setAttribute("data-prog", "__" + (b3 + 1));
                    b1.index = b3;
                    b1.setAttribute("id", "");
                    if (!b1.getAttribute("data-title")) {
                        var b5 = b1.querySelector("h1");
                        if (b5 != null && b5.textContent.lenght != "") {
                            b1.setAttribute("data-title", b5.textContent)
                        }
                    }
                    br(b1, b2, b3);
                    b0.push(b1);
                    var b4 = b1.querySelectorAll(A);
                    bs[b2][b3] = b4;
                    bC[b2][b3] = -1
                }
                bm.push(b0)
            }
            bB = bN.length;
            ad();
            bk()
        }
        function br(b6, b7, b3) {
            if (T) {
                if (bt[b7] == undefined) {
                    bt[b7] = []
                }
                if (bt[b7][b3] == undefined) {
                    bt[b7][b3] = []
                }
                var b5 = b6.querySelectorAll(".parallax");
                if (b5.length > 0) {
                    for (var b4 = 0; b4 < b5.length; b4++) {
                        var b0 = b5[b4];
                        var b2 = h;
                        var b1 = g;
                        if (b0.getAttribute("data-parallax") != null) {
                            var b8 = b0.getAttribute("data-parallax").split(",");
                            b2 = b1 = b8[0];
                            if (b8.length > 1) {
                                b1 = b8[1]
                            }
                        }
                        b0.pX = b2;
                        b0.pY = b1;
                        bt[b7][b3].push(b0)
                    }
                }
            }
        }
        function bi() {
            return bt
        }
        function bk() {
            for (var b1 = 0; b1 < bw.length; b1++) {
                var b0 = bw[b1];
                b0.x = b0.offsetLeft + b0.parentNode.offsetLeft;
                b0.y = b0.offsetTop + b0.parentNode.offsetTop
            }
        }
        function be(b3, b2, b4) {
            var b1 = bq;
            var b0 = b3 == !M;
            if (b2 == true && bs[bT][bq].length > 0 && bC[bT][bq] < bs[bT][bq].length - 1 && b0 != true && b4 == false) {
                bn(bT, bq)
            } else {
                b1 = 0;
                if (b0 == true && bT + 1 < bm.length - 1) {
                    b1 = 0
                } else {
                    if (b0 != true || p == true || bT + 1 > bm.length - 1) {
                        b1 = bq
                    }
                }
                bT = Math.min(bT + 1, bm.length - 1);
                return bI(bm[bT], b1, b4)
            }
            return bO(bm[bT][bq], b4)
        }
        function bM(b3, b2, b4) {
            var b1 = bq;
            var b0 = b3 == !M;
            if (b2 == true && bs[bT][bq].length > 0 && bC[bT][bq] >= 0 && b0 != true && b4 == false) {
                bV(bT, bq)
            } else {
                var b1 = 0;
                b1 = 0;
                if (b0 == true && bT - 1 >= 0) {
                    b1 = 0
                } else {
                    if (b0 != true || p == true || bT - 1 < 0) {
                        b1 = bq
                    }
                }
                bT = Math.max(bT - 1, 0);
                return bI(bm[bT], b1, b4)
            }
            return bO(bm[bT][bq], b4)
        }
        function bI(b4, b1, b3) {
            var b2 = b4[b1];
            if (b2 == undefined) {
                for (var b0 = b1; b0 >= 0; b0--) {
                    if (b4[b0] != undefined) {
                        b2 = b4[b0];
                        b1 = b0;
                        break
                    }
                }
            }
            bq = b1;
            if (!H) {
                bD()
            }
            return bO(b2, b3)
        }
        function a9(b0, b1) {
            if (bs[bT][bq].length > 0 && bC[bT][bq] < bs[bT][bq].length - 1 && b0 != true && b1 == false) {
                bn(bT, bq)
            } else {
                if (bm[bT][bq + 1] == undefined && bm[bT + 1] != undefined) {
                    bT += 1;
                    bq = 0
                } else {
                    bq = Math.min(bq + 1, bm[bT].length - 1)
                }
            }
            return bO(bm[bT][bq], b1)
        }
        function bW(b0, b1) {
            if (bs[bT][bq].length > 0 && bC[bT][bq] >= 0 && b0 != true && b1 == false) {
                bV(bT, bq)
            } else {
                if (bq == 0 && bm[bT - 1] != undefined) {
                    bT -= 1;
                    bq = bm[bT].length - 1
                } else {
                    bq = Math.max(bq - 1, 0)
                }
            }
            return bO(bm[bT][bq], b1)
        }
        function bO(b0, b1) {
            if (b1 == true) {
                bb(b0);
                return
            } else {
                return b0
            }
        }
        function bn(b0, b2, b3) {
            if (b3 != undefined) {
                bC[b0][b2] = b3
            } else {
                b3 = bC[b0][b2] += 1
            }
            for (var b1 = 0; b1 <= b3; b1++) {
                Brav1Toolbox.addClass(bs[b0][b2][b1], ah);
                Brav1Toolbox.removeClass(bs[b0][b2][b1], aI)
            }
            Brav1Toolbox.addClass(bs[b0][b2][b3], aI)
        }
        function bV(b0, b2, b3) {
            if (b3 != undefined) {
                bC[b0][b2] = b3
            } else {
                b3 = bC[b0][b2]
            }
            for (var b1 = 0; b1 < bs[b0][b2].length; b1++) {
                if (b1 >= b3) {
                    Brav1Toolbox.removeClass(bs[b0][b2][b1], ah);
                    Brav1Toolbox.removeClass(bs[b0][b2][b1], ax)
                }
                Brav1Toolbox.removeClass(bs[b0][b2][b1], aI)
            }
            b3 -= 1;
            if (b3 >= 0) {
                Brav1Toolbox.addClass(bs[b0][b2][b3], aI)
            }
            bC[b0][b2] = b3
        }
        function bg() {
            for (var b0 = 0; b0 < bJ.length; b0++) {
                Brav1Toolbox.addClass(bJ[b0], ax)
            }
        }
        function bv() {
            for (var b0 = 0; b0 < bJ.length; b0++) {
                Brav1Toolbox.removeClass(bJ[b0], ax)
            }
        }
        function bD() {
            for (var b4 = 0; b4 < bs.length; b4++) {
                var b1 = bs[b4];
                for (var b2 = 0; b2 < b1.length; b2++) {
                    var b0 = b1[b2];
                    if (b0.length > 0) {
                        if (b4 > bT) {
                            for (var b3 = b0.length - 1; b3 >= 0; b3--) {
                                bV(b4, b2, b3)
                            }
                        } else {
                            if (b4 < bT) {
                                for (var b3 = 0; b3 < b0.length; b3++) {
                                    bn(b4, b2, b3)
                                }
                            } else {
                                if (b4 == bT) {
                                    if (b2 > bq) {
                                        for (var b3 = b0.length - 1; b3 >= 0; b3--) {
                                            bV(b4, b2, b3)
                                        }
                                    } else {
                                        if (b2 < bq) {
                                            for (var b3 = 0; b3 < b0.length; b3++) {
                                                bn(b4, b2, b3)
                                            }
                                        } else {
                                            if (b2 == bq) {
                                                if (p == true && (D.section > z.getPageIndex().section || D.page > z.getPageIndex().page)) {
                                                    for (var b3 = 0; b3 < b0.length; b3++) {
                                                        bn(b4, b2, b3)
                                                    }
                                                } else {
                                                    for (var b3 = b0.length - 1; b3 >= 0; b3--) {
                                                        bV(b4, b2, b3)
                                                    }
                                                }
                                                if (p == false) {
                                                    bC[b4][b2] = -1
                                                } else {
                                                    if (D.section > z.getPageIndex().section || D.page > z.getPageIndex().page) {
                                                        bC[b4][b2] = b0.length - 1
                                                    } else {
                                                        bC[b4][b2] = -1
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        function bp(b0) {
            if (b0) {}
            return bT
        }
        function a8(b0) {
            if (b0) {}
            return bq
        }
        function bR() {
            return bN
        }
        function bx(b0) {
            return bm[b0]
        }
        function bz() {
            return bw
        }
        function bY() {
            return bB
        }
        function bE() {
            return bZ
        }
        function bA() {
            return bK
        }
        function bL(b2) {
            var b1 = bT;
            var b0 = bq;
            if (b2 != undefined) {
                b1 = b2.parentNode.index;
                b0 = b2.index
            }
            return {
                section: b1,
                page: b0
            }
        }
        function bS(b0) {
            return bN[b0]
        }
        function bu(b0, b1) {
            return bm[b1][b0]
        }
        function bh() {
            return bN[bT]
        }
        function bP() {
            return bm[bT][bq]
        }
        function bH() {
            return bs[bT][bq][e()]
        }
        function e() {
            return bC[bT][bq]
        }
        function by() {
            return bT < bN.length - 1
        }
        function bf() {
            return bT > 0
        }
        function bl() {
            return bq < bm[bT].length - 1
        }
        function bd() {
            return bq > 0
        }
        function bG() {
            if (bT == 0 && bq == 0) {
                return 0
            }
            var b1 = 0;
            for (var b0 = 0; b0 < bT; b0++) {
                b1 += bm[b0].length
            }
            b1 += bm[bT][bq].index + 1;
            return b1
        }
        function bQ(b3) {
            if (b3 != undefined) {
                bq = bL(b3).page;
                bT = bL(b3).section
            }
            var b2 = "";
            var b0 = bN[bT];
            b2 += d(b0);
            if (bm[bT].length > 1) {
                var b1 = bm[bT][bq];
                b2 += "/" + d(b1)
            }
            return b2
        }
        function bc(b4) {
            var b5 = aZ(b4);
            if (b5) {
                var b1 = b5.parentNode;
                for (var b0 = 0; b0 < bm.length; b0++) {
                    var b3 = bm[b0];
                    if (bN[b0] === b1) {
                        bT = b0;
                        for (var b2 = 0; b2 < b3.length; b2++) {
                            if (b3[b2] === b5) {
                                bq = b2;
                                break
                            }
                        }
                    }
                }
                bD()
            }
            return b5
        }
        function bb(b6, b0) {
            var b4 = b6.parentNode.index;
            for (var b1 = 0; b1 < bm.length; b1++) {
                var b3 = bm[b1];
                for (var b2 = 0; b2 < b3.length; b2++) {
                    var b5 = b3[b2];
                    Brav1Toolbox.removeClass(b5, "past-section");
                    Brav1Toolbox.removeClass(b5, "future-section");
                    Brav1Toolbox.removeClass(b5, "past-page");
                    Brav1Toolbox.removeClass(b5, "future-page");
                    if (b5 !== b6) {
                        Brav1Toolbox.removeClass(b5, "hilite");
                        if (H == false && b5 !== bP()) {
                            Brav1Toolbox.removeClass(b5, "actual")
                        }
                        if (b1 < b4) {
                            Brav1Toolbox.addClass(b5, "past-section")
                        } else {
                            if (b1 > b4) {
                                Brav1Toolbox.addClass(b5, "future-section")
                            }
                        }
                        if (b5.index < b6.index) {
                            Brav1Toolbox.addClass(b5, "past-page")
                        } else {
                            if (b5.index > b6.index) {
                                Brav1Toolbox.addClass(b5, "future-page")
                            }
                        }
                    }
                }
            }
            Brav1Toolbox.addClass(b6, "hilite");
            if (b0) {
                bX(b6)
            }
            bF = b6
        }
        function bo() {
            return bF
        }
        function bX(b0) {
            Brav1Toolbox.addClass(b0, "actual")
        }
        ba();
        return {
            update: ba,
            updateFragments: bD,
            showFragments: bg,
            hideFragments: bv,
            getSection: bp,
            getPage: a8,
            getSections: bR,
            getPages: bx,
            getAllPages: bz,
            getNextSection: be,
            getPrevSection: bM,
            getNextPage: a9,
            getPrevPage: bW,
            getSectionsLength: bY,
            getPagesLength: bE,
            getPagesTotalLength: bA,
            getPageIndex: bL,
            getSectionByIndex: bS,
            getPageByIndex: bu,
            getCurrentSection: bh,
            getCurrentPage: bP,
            getCurrentFragment: bH,
            getCurrentFragmentIndex: e,
            getProgress: bG,
            getHash: bQ,
            setPage: bc,
            switchActivePage: bb,
            getCurrentHilited: bo,
            hasNextSection: by,
            hasPrevSection: bf,
            hasNextPage: bl,
            hasPrevPage: bd,
            updateOffsets: bk,
            getParallaxElements: bi
        }
    }
    )();
    if (I) {
        if (l) {
            Brav1Toolbox.addListener(document, "touchend", a7, false)
        } else {
            Brav1Toolbox.addListener(document, "click", a7, false)
        }
    }
    function a7(bd) {
        var a9 = bd.target.getAttribute("href");
        if (a9 && a9.substr(0, 1) == "#") {
            bd.target.blur();
            bd.preventDefault();
            var bc = a9;
            var ba = z.setPage(bc);
            V(ba, true, true)
        }
        if (H) {
            var ba = bd.target;
            while (ba && !Brav1Toolbox.hasClass(ba, aM)) {
                ba = ba.parentNode
            }
            if (Brav1Toolbox.hasClass(ba, aM)) {
                bd.preventDefault();
                V(ba, null, true)
            }
        }
        if (Brav1Toolbox.hasClass(bd.target, k)) {
            bd.preventDefault();
            var bb = Number(F(bd.target.getAttribute("data-section")));
            var a8 = Number(F(bd.target.getAttribute("data-page")));
            aB(bb, a8)
        }
    }
    if (aA == false && window.history.pushState) {
        window.onpopstate = B
    } else {
        aA = true
    }
    function B(ba) {
        aA = false;
        var a9;
        if (ba.state) {
            a9 = ba.state.token.replace("#/", "")
        } else {
            a9 = document.location.hash.replace("#/", "")
        }
        var a8 = z.setPage(a9);
        V(a8, false)
    }
    Brav1Toolbox.addListener(window, "hashchange", L);
    function L(ba, bb) {
        if (aA || bb) {
            var a9 = document.location.hash.replace("#/", "");
            var a8 = z.setPage(a9);
            V(a8, false)
        }
    }
    var a6 = J.offsetX;
    var a4 = 0;
    var Z = 0;
    var W = 0;
    var au = 0;
    var ar = 0;
    var aq = 0;
    var aS = "x";
    var y = 100;
    aL.addEventListener("touchstart", aP, false);
    aL.addEventListener("touchmove", aY, false);
    aL.addEventListener("touchend", Y, false);
    function aP(a8) {
        au = 0;
        ar = 0;
        a8.preventDefault();
        a8 = an(a8);
        Z = a8.clientX;
        W = a8.clientY;
        aq = 1;
        var a9 = v();
        a6 = a9.x;
        a4 = a9.y
    }
    function aY(a8) {
        a8.preventDefault();
        a8 = an(a8);
        au = a8.clientX - Z;
        ar = a8.clientY - W
    }
    function Y(a8) {
        a8 = an(a8);
        aq = 0;
        aS = Math.abs(au) >= Math.abs(ar) ? "x" : "y";
        if (aS == "x" && Math.abs(au) >= y) {
            if (au > 0) {
                b();
                return
            } else {
                if (au < 0) {
                    K();
                    return
                }
            }
        } else {
            if (ar > 0 && Math.abs(ar) >= y) {
                aH();
                return
            } else {
                if (ar < 0) {
                    ae();
                    return
                }
            }
        }
    }
    function an(a8) {
        if (a8.touches) {
            a8 = a8.touches[0]
        }
        return a8
    }
    function v() {
        var bb = J.style[Brav1Toolbox.getPrefixed("transform")];
        var ba = bb.indexOf("translateX(") + 11;
        var a8 = bb.substring(ba, bb.indexOf(")", ba));
        if (a8.indexOf("%") != -1) {
            a8 = a8.replace("%", "");
            a8 = (parseInt(a8) / 100) * window.innerWidth
        } else {
            if (a8.indexOf("px") != -1) {
                a8 = parseInt(a8.replace("px", ""))
            }
        }
        var a9 = bb.indexOf("translateY(") + 11;
        var e = bb.substring(a9, bb.indexOf(")", a9));
        if (e.indexOf("%") != -1) {
            e = e.replace("%", "");
            e = (parseInt(e) / 100) * window.innerHeight
        } else {
            if (e.indexOf("px") != -1) {
                e = parseInt(e.replace("px", ""))
            }
        }
        return {
            x: a8,
            y: e
        }
    }
    var P = true;
    Brav1Toolbox.addListener(window, "scroll", f);
    function f(a8) {
        a8.preventDefault();
        ad()
    }
    var aR = (function n() {
        var ba = NaN;
        function a9() {
            e();
            if (!H) {
                ba = setTimeout(a8, 300)
            }
        }
        function e() {
            clearTimeout(ba)
        }
        function a8() {
            z.updateOffsets();
            V()
        }
        Brav1Toolbox.addListener(window, "resize", a9);
        window.addEventListener("orientationchange", a9, false);
        return {
            enable: a9,
            disable: e,
        }
    }
    )();
    function aZ(a8) {
        if (a8.length > 0) {
            var e = a8.replace("#/", "").split("/");
            var ba = document.querySelector(aU + "[data-id=__" + e[0] + "]") || document.querySelector(aU + "[data-prog=__" + e[0] + "]");
            if (ba != null) {
                var a9 = null;
                if (e.length > 1) {
                    a9 = ba.querySelector(aK + "[data-id=__" + e[1] + "]") || ba.querySelector(aK + "[data-prog=__" + e[1] + "]")
                }
                if (a9 == null) {
                    a9 = ba.querySelector(aK)
                }
                return a9
            }
        }
        return
    }
    function aQ() {
        z.update();
        L(null, true)
    }
    function a1(bb) {
        var ba = aF;
        var e = z.getCurrentPage().getAttribute("data-title");
        if (e == null) {
            var a9 = bb.split("/");
            for (var a8 = 0; a8 < a9.length; a8++) {
                ba += " | " + a9[a8]
            }
        } else {
            if (z.getCurrentSection().getAttribute("data-title") != null) {
                ba += " | " + z.getCurrentSection().getAttribute("data-title")
            }
            ba += " | " + e
        }
        document.title = ba
    }
    function d(e) {
        return (e.getAttribute("data-id") != null ? e.getAttribute("data-id").replace(/__/, "") : e.getAttribute("data-prog").replace(/__/, ""))
    }
    function aV(e) {
        if (e.substr(0, 2) != "__") {
            return "__" + e
        } else {
            return e
        }
    }
    function F(e) {
        if (e.substr(0, 2) == "__") {
            return e.replace(/__/, "")
        } else {
            return e
        }
    }
    function V(bb, ba, bd) {
        ba = ba == false ? ba : true;
        if (!bb) {
            if (z.getCurrentPage() != null) {
                bb = z.getCurrentPage()
            } else {
                bb = document.querySelector(aK)
            }
            ba = true
        }
        a3(bb);
        t(bb);
        if (H) {
            af(false, false)
        }
        var bc = z.getHash(bb);
        if (bd == true) {
            z.updateFragments()
        }
        var e = z.getPageIndex(bb);
        if (D.section != e.section || D.page != e.page) {
            if (aJ != null && ba != false && z.getCurrentFragmentIndex() == -1) {
                var a9 = {
                    token: bc
                };
                var a8 = "#/" + bc;
                ac = a8;
                window.history.pushState(a9, null, ac)
            } else {
                document.location.hash = "/" + bc
            }
        }
        a1(bc);
        aO();
        D = e;
        z.switchActivePage(bb, true);
        if (aE) {
            a5()
        }
    }
    function aO() {
        var e = z.getPageIndex();
        Brav1Toolbox.dispatchEvent(s, {
            section: z.getCurrentSection(),
            page: z.getCurrentPage(),
            sectionIndex: e.section,
            pageIndex: e.page,
            pastSectionIndex: D.section,
            pastPageIndex: D.page,
            prevSection: z.hasPrevSection(),
            nextSection: z.hasNextSection(),
            prevPage: z.hasPrevPage(),
            nextPage: z.hasNextPage(),
            fragment: z.getCurrentFragment(),
            fragmentIndex: z.getCurrentFragmentIndex(),
            isOverview: H,
            progress: z.getProgress(),
            total: z.getPagesTotalLength()
        })
    }
    function a3(a9) {
        var a8;
        var ba;
        var e = z.getPageIndex(a9);
        if (C == true) {
            a8 = a9.x;
            ba = a9.y
        } else {
            a8 = e.section;
            ba = e.page
        }
        if (Brav1Toolbox.testCSS("transform")) {
            if (C) {
                J.style[Brav1Toolbox.getPrefixed("transform")] = "translateX(" + -a8 + "px) translateY(" + -ba + "px)"
            } else {
                J.style[Brav1Toolbox.getPrefixed("transform")] = "translateX(" + -a8 * 100 + "%) translateY(" + -ba * 100 + "%)"
            }
        } else {
            if (C) {
                J.style.top = -ba + "px";
                J.style.left = -a8 + "px"
            } else {
                J.style.top = -ba * 100 + "%";
                J.style.left = -a8 * 100 + "%"
            }
        }
        ad()
    }
    function t(be) {
        if (T) {
            var a9 = z.getPageIndex(be);
            var bd = z.getParallaxElements();
            for (var bb = 0; bb < bd.length; bb++) {
                var ba = bd[bb];
                if (ba != undefined) {
                    for (var bg = 0; bg < ba.length; bg++) {
                        var bf = ba[bg];
                        if (bf != undefined) {
                            for (var bc = 0; bc < bf.length; bc++) {
                                var bh = bf[bc];
                                var a8 = 0;
                                var e = 0;
                                if (a9.section < bb) {
                                    a8 = bh.pX
                                } else {
                                    if (a9.section > bb) {
                                        a8 = -bh.pX
                                    }
                                }
                                if (a9.page < bg) {
                                    e = bh.pY
                                } else {
                                    if (a9.page > bg) {
                                        e = -bh.pY
                                    }
                                }
                                if (ao) {
                                    bh.style[Brav1Toolbox.getPrefixed("transform")] = "translateX(" + a8 + "px) translateY(" + e + "px)"
                                } else {
                                    bh.style[Brav1Toolbox.getPrefixed("transform")] = "translateX(" + a8 + "%) translateY(" + e + "%)"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    function ad() {
        window.scrollTo(0, 0)
    }
    var az = null;
    var aw = null;
    function aW() {
        var a8 = document.createDocumentFragment();
        az = document.createElement("div");
        az.className = ak;
        a8.appendChild(az);
        for (var ba = 0; ba < z.getSectionsLength(); ba++) {
            var e = document.createElement("div");
            e.setAttribute("data-section", "__" + ba);
            e.className = ab;
            Brav1Toolbox.addClass(e, "thumb-section-" + ba);
            var a9 = z.getPages(ba);
            for (var bb = 0; bb < a9.length; bb++) {
                var bc = document.createElement("div");
                bc.className = k;
                bc.setAttribute("data-section", "__" + ba);
                bc.setAttribute("data-page", "__" + bb);
                Brav1Toolbox.addClass(bc, "thumb-page-" + bb);
                e.appendChild(bc)
            }
            az.appendChild(e)
        }
        u.appendChild(az)
    }
    function w() {
        if (az != null) {
            u.removeChild(az);
            az = null
        }
    }
    function a5() {
        if (az != null) {
            var ba = az.querySelectorAll(S);
            for (var a9 = 0; a9 < ba.length; a9++) {
                var bb = ba[a9];
                var a8 = Number(F(bb.getAttribute("data-section")));
                var e = Number(F(bb.getAttribute("data-page")));
                if (a8 == z.getPageIndex().section && e == z.getPageIndex().page) {
                    Brav1Toolbox.addClass(ba[a9], "actual")
                } else {
                    Brav1Toolbox.removeClass(ba[a9], "actual")
                }
            }
        }
    }
    function ag() {
        return az
    }
    function af(e, a8) {
        if (H) {
            o(e, a8)
        } else {
            O = z.getCurrentPage();
            U()
        }
    }
    function o(e, a8) {
        H = false;
        Brav1Toolbox.removeClass(u, "ft-overview");
        z.hideFragments();
        a8 = a8 === false ? false : true;
        if (a8 == true) {
            if (e == true) {
                V(O)
            } else {
                V()
            }
        }
    }
    function U() {
        H = true;
        Brav1Toolbox.addClass(u, "ft-overview");
        z.showFragments();
        if (av == false) {
            R(true)
        } else {
            Q(true)
        }
        aO()
    }
    function R(ba) {
        if (ba) {
            var a9 = 100 / z.getSectionsLength();
            var a8 = 100 / z.getPagesLength();
            var bb = Math.min(a9, a8) * 0.9;
            var e = (100 - z.getSectionsLength() * bb) / 2;
            var bc = (100 - z.getPagesLength() * bb) / 2;
            J.style[Brav1Toolbox.getPrefixed("transform")] = "translate(" + e + "%, " + bc + "%) scale(" + bb / 100 + ", " + bb / 100 + ")"
        }
    }
    function Q(a8) {
        if (a8) {
            var ba = aN;
            var a9 = z.getPageIndex();
            var e = 50 - (ba * a9.section) - (ba / 2);
            var bb = 50 - (ba * a9.page) - (ba / 2);
            J.style[Brav1Toolbox.getPrefixed("transform")] = "translate(" + e + "%, " + bb + "%) scale(" + ba / 100 + ", " + ba / 100 + ")"
        }
    }
    var ismoving = (/phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone|webOS|android/i.test(navigator.userAgent));
    if(ismoving==true){
        $("#my-love-btn").text("点击这里有惊喜哦");
        $("#my-start").text("向上滑动")
        $("#my-love-btn").on("click",function(){
            af(true);
        });
    }
    Brav1Toolbox.addListener(window, "keydown", E);
    Brav1Toolbox.addListener(window, "keyup", ay);
    function E(a9) {
        var a8 = a9.target.tagName;
        if (a8 != "INPUT" && a8 != "TEXTAREA" && a8 != "SELECT") {
            if (a9.keyCode >= 37 && a9.keyCode <= 40) {
                a9.preventDefault()
            }
        }
    }
    function ay(ba) {
        var a8 = ba.target.tagName;
        var a9;
        if (a8 != "INPUT" && a8 != "TEXTAREA" && a8 != "SELECT") {
            ba.preventDefault();
            switch (ba.keyCode) {
            case 27:
                af(true);
                break;
            case 33:
                c();
                break;
            case 34:
                aj();
                break;
            case 35:
                aD();
                break;
            case 36:
                i();
                break;
            case 37:
                b(ba.shiftKey);
                break;
            case 39:
                K(ba.shiftKey);
                break;
            case 38:
                aH(ba.shiftKey);
                break;
            case 40:
                ae(ba.shiftKey);
                break;
            case 13:
                if (H) {
                    aB(z.getCurrentHilited())
                }
                break;
            default:
                break
            }
        }
    }
    function a() {
        if (aE && az == null) {
            aW()
        }
        if (document.location.hash.length > 0) {
            Brav1Toolbox.addClass(J, "no-transition");
            L(null, true);
            Brav1Toolbox.removeClass(J, "no-transition")
        } else {
            if (a.arguments.length > 0) {
                aB.apply(this, a.arguments)
            } else {
                aB(0, 0);
                a5()
            }
        }
    }
    function K(e) {
        var a8 = z.getNextSection(e, x, H);
        if (a8 != undefined) {
            V(a8)
        } else {
            if (H && av) {
                U()
            }
        }
    }
    function b(e) {
        var a8 = z.getPrevSection(e, x, H);
        if (a8 != undefined) {
            V(a8)
        } else {
            if (H && av) {
                U()
            }
        }
    }
    function ae(e) {
        var a8 = z.getNextPage(e, H);
        if (a8 != undefined) {
            V(a8)
        } else {
            if (H && av) {
                U()
            }
        }
    }
    function aH(e) {
        var a8 = z.getPrevPage(e, H);
        if (a8 != undefined) {
            V(a8)
        } else {
            if (H && av) {
                U()
            }
        }
    }
    function aB() {
        var a8 = aB.arguments;
        if (a8.length > 0) {
            if (a8.length == 1) {
                if (Brav1Toolbox.typeOf(a8[0]) === "Object") {
                    var bc = a8[0];
                    var bb = bc.section;
                    var ba = bc.page;
                    if (bb != null && bb != undefined) {
                        var e = document.querySelector(aU + "[data-id=" + aV(bb) + "]");
                        if (ba != null && ba != undefined) {
                            var a9 = e.querySelector(aK + "[data-id=" + aV(ba) + "]");
                            if (a9 != null) {
                                V(a9);
                                return
                            }
                        }
                    }
                } else {
                    if (a8[0].nodeName != undefined) {
                        V(a8[0], null, true)
                    }
                }
            }
            if (Brav1Toolbox.typeOf(a8[0]) === "Number" || a8[0] === 0) {
                var a9 = z.getPageByIndex(a8[1], a8[0]);
                V(a9);
                return
            }
        }
    }
    function i() {
        aB(0, 0)
    }
    function aD() {
        var e = z.getSectionsLength() - 1;
        aB(e, z.getPages(e).length - 1)
    }
    function c() {
        var e = z.getPageIndex();
        aB(e.section, 0)
    }
    function aj() {
        var e = z.getPageIndex();
        aB(e.section, z.getPages(e.section).length - 1)
    }
    function am(a9, a8, e) {
        Brav1Toolbox.addListener(document, a9, a8, e)
    }
    function ap(e) {
        x = e;
        ai(e)
    }
    function ai(e) {
        p = e
    }
    function a0(e) {
        aJ = e
    }
    function a2(e) {
        C = e;
        V()
    }
    function r(e) {
        M = e
    }
    function q(e) {
        M = !e
    }
    function X(e) {
        av = e
    }
    function al(e) {
        aX = e
    }
    function aT(e) {
        aE = e;
        if (aE) {
            if (az == null) {
                aW()
            }
            a5()
        } else {
            if (az != null) {
                w()
            }
        }
    }
    function at(e, a8) {
        h = e;
        g = a8 == undefined ? h : a8;
        z.update()
    }
    function aC(e) {
        ao = e
    }
    return {
        start: a,
        updateNavigation: aQ,
        nextSection: K,
        prevSection: b,
        next: ae,
        prev: aH,
        nextFragment: ae,
        prevFragment: aH,
        gotoPage: aB,
        gotoHome: i,
        gotoTop: c,
        gotoBottom: aj,
        gotoEnd: aD,
        toggleOverview: af,
        fragmentsOnSide: ap,
        fragmentsOnBack: ai,
        useHistory: a0,
        slideInPx: a2,
        sectionsSlideToTop: r,
        gridNavigation: q,
        useOverviewVariant: X,
        twoStepsSlide: al,
        showProgress: aT,
        addEventListener: am,
        defaultParallaxValues: at,
        parallaxInPx: aC,
        getDefaultProgress: ag
    }
}
)();

(function(h, o, g) {
    var p = function() {
        for (var b = /audio(.min)?.js.*/, a = document.getElementsByTagName("script"), c = 0, d = a.length; c < d; c++) {
            var e = a[c].getAttribute("src");
            if (b.test(e))
                return e.replace(b, "")
        }
    }();
    g[h] = {
        instanceCount: 0,
        instances: {},
        flashSource: '      <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" id="$1" width="1" height="1" name="$1" style="position: absolute; left: -1px;">         <param name="movie" value="$2?playerInstance=' + h + '.instances[\'$1\']&datetime=$3">         <param name="allowscriptaccess" value="always">         <embed name="$1" src="$2?playerInstance=' + h + '.instances[\'$1\']&datetime=$3" width="1" height="1" allowscriptaccess="always">       </object>',
        settings: {
            autoplay: false,
            loop: false,
            preload: true,
            imageLocation: p + "player-graphics.gif",
            swfLocation: p + "audiojs.swf",
            useFlash: function() {
                var b = document.createElement("audio");
                return !(b.canPlayType && b.canPlayType("audio/mpeg;").replace(/no/, ""))
            }(),
            hasFlash: function() {
                if (navigator.plugins && navigator.plugins.length && navigator.plugins["Shockwave Flash"])
                    return true;
                else if (navigator.mimeTypes && navigator.mimeTypes.length) {
                    var b = navigator.mimeTypes["application/x-shockwave-flash"];
                    return b && b.enabledPlugin
                } else
                    try {
                        new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                        return true
                    } catch (a) {}
                return false
            }(),
            createPlayer: {
                markup: '          <div class="play-pause">             <p class="play"></p>             <p class="pause"></p>             <p class="loading"></p>             <p class="error"></p>           </div>           <div class="scrubber">             <div class="progress"></div>             <div class="loaded"></div>           </div>           <div class="time">             <em class="played">00:00</em>/<strong class="duration">00:00</strong>           </div>           <div class="error-message"></div>',
                playPauseClass: "play-pause",
                scrubberClass: "scrubber",
                progressClass: "progress",
                loaderClass: "loaded",
                timeClass: "time",
                durationClass: "duration",
                playedClass: "played",
                errorMessageClass: "error-message",
                playingClass: "playing",
                loadingClass: "loading",
                errorClass: "error"
            },
            css: '        .audiojs audio { position: absolute; left: -1px; }         .audiojs { width: 460px; height: 36px; background: #404040; overflow: hidden; font-family: monospace; font-size: 12px;           background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #444), color-stop(0.5, #555), color-stop(0.51, #444), color-stop(1, #444));           background-image: -moz-linear-gradient(center top, #444 0%, #555 50%, #444 51%, #444 100%);           -webkit-box-shadow: 1px 1px 8px rgba(0, 0, 0, 0.3); -moz-box-shadow: 1px 1px 8px rgba(0, 0, 0, 0.3);           -o-box-shadow: 1px 1px 8px rgba(0, 0, 0, 0.3); box-shadow: 1px 1px 8px rgba(0, 0, 0, 0.3); }         .audiojs .play-pause { width: 25px; height: 40px; padding: 4px 6px; margin: 0px; float: left; overflow: hidden; border-right: 1px solid #000; }         .audiojs p { display: none; width: 25px; height: 40px; margin: 0px; cursor: pointer; }         .audiojs .play { display: block; }         .audiojs .scrubber { position: relative; float: left; width: 280px; background: #5a5a5a; height: 14px; margin: 10px; border-top: 1px solid #3f3f3f; border-left: 0px; border-bottom: 0px; overflow: hidden; }         .audiojs .progress { position: absolute; top: 0px; left: 0px; height: 14px; width: 0px; background: #ccc; z-index: 1;           background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #ccc), color-stop(0.5, #ddd), color-stop(0.51, #ccc), color-stop(1, #ccc));           background-image: -moz-linear-gradient(center top, #ccc 0%, #ddd 50%, #ccc 51%, #ccc 100%); }         .audiojs .loaded { position: absolute; top: 0px; left: 0px; height: 14px; width: 0px; background: #000;           background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #222), color-stop(0.5, #333), color-stop(0.51, #222), color-stop(1, #222));           background-image: -moz-linear-gradient(center top, #222 0%, #333 50%, #222 51%, #222 100%); }         .audiojs .time { float: left; height: 36px; line-height: 36px; margin: 0px 0px 0px 6px; padding: 0px 6px 0px 12px; border-left: 1px solid #000; color: #ddd; text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.5); }         .audiojs .time em { padding: 0px 2px 0px 0px; color: #f9f9f9; font-style: normal; }         .audiojs .time strong { padding: 0px 0px 0px 2px; font-weight: normal; }         .audiojs .error-message { float: left; display: none; margin: 0px 10px; height: 36px; width: 400px; overflow: hidden; line-height: 36px; white-space: nowrap; color: #fff;           text-overflow: ellipsis; -o-text-overflow: ellipsis; -icab-text-overflow: ellipsis; -khtml-text-overflow: ellipsis; -moz-text-overflow: ellipsis; -webkit-text-overflow: ellipsis; }         .audiojs .error-message a { color: #eee; text-decoration: none; padding-bottom: 1px; border-bottom: 1px solid #999; white-space: wrap; }                 .audiojs .play { background: url("$1") -2px -1px no-repeat; }         .audiojs .loading { background: url("$1") -2px -31px no-repeat; }         .audiojs .error { background: url("$1") -2px -61px no-repeat; }         .audiojs .pause { background: url("$1") -2px -91px no-repeat; }                 .playing .play, .playing .loading, .playing .error { display: none; }         .playing .pause { display: block; }                 .loading .play, .loading .pause, .loading .error { display: none; }         .loading .loading { display: block; }                 .error .time, .error .play, .error .pause, .error .scrubber, .error .loading { display: none; }         .error .error { display: block; }         .error .play-pause p { cursor: auto; }         .error .error-message { display: block; }',
            trackEnded: function() {},
            flashError: function() {
                var b = this.settings.createPlayer
                  , a = j(b.errorMessageClass, this.wrapper)
                  , c = 'Missing <a href="http://get.adobe.com/flashplayer/">flash player</a> plugin.';
                if (this.mp3)
                    c += ' <a href="' + this.mp3 + '">Download audio file</a>.';
                g[h].helpers.removeClass(this.wrapper, b.loadingClass);
                g[h].helpers.addClass(this.wrapper, b.errorClass);
                a.innerHTML = c
            },
            loadError: function() {
                var b = this.settings.createPlayer
                  , a = j(b.errorMessageClass, this.wrapper);
                g[h].helpers.removeClass(this.wrapper, b.loadingClass);
                g[h].helpers.addClass(this.wrapper, b.errorClass);
                a.innerHTML = 'Error loading: "' + this.mp3 + '"'
            },
            init: function() {
                g[h].helpers.addClass(this.wrapper, this.settings.createPlayer.loadingClass)
            },
            loadStarted: function() {
                var b = this.settings.createPlayer
                  , a = j(b.durationClass, this.wrapper)
                  , c = Math.floor(this.duration / 60)
                  , d = Math.floor(this.duration % 60);
                g[h].helpers.removeClass(this.wrapper, b.loadingClass);
                a.innerHTML = (c < 10 ? "0" : "") + c + ":" + (d < 10 ? "0" : "") + d
            },
            loadProgress: function(b) {
                var a = this.settings.createPlayer
                  , c = j(a.scrubberClass, this.wrapper);
                j(a.loaderClass, this.wrapper).style.width = c.offsetWidth * b + "px"
            },
            playPause: function() {
                this.playing ? this.settings.play() : this.settings.pause()
            },
            play: function() {
                g[h].helpers.addClass(this.wrapper, this.settings.createPlayer.playingClass)
            },
            pause: function() {
                g[h].helpers.removeClass(this.wrapper, this.settings.createPlayer.playingClass)
            },
            updatePlayhead: function(b) {
                var a = this.settings.createPlayer
                  , c = j(a.scrubberClass, this.wrapper);
                j(a.progressClass, this.wrapper).style.width = c.offsetWidth * b + "px";
                a = j(a.playedClass, this.wrapper);
                c = this.duration * b;
                b = Math.floor(c / 60);
                c = Math.floor(c % 60);
                a.innerHTML = (b < 10 ? "0" : "") + b + ":" + (c < 10 ? "0" : "") + c
            }
        },
        create: function(b, a) {
            a = a || {};
            return b.length ? this.createAll(a, b) : this.newInstance(b, a)
        },
        createAll: function(b, a) {
            var c = a || document.getElementsByTagName("audio")
              , d = [];
            b = b || {};
            for (var e = 0, i = c.length; e < i; e++)
                d.push(this.newInstance(c[e], b));
            return d
        },
        newInstance: function(b, a) {
            var c = this.helpers.clone(this.settings)
              , d = "audiojs" + this.instanceCount
              , e = "audiojs_wrapper" + this.instanceCount;
            this.instanceCount++;
            if (b.getAttribute("autoplay") != null)
                c.autoplay = true;
            if (b.getAttribute("loop") != null)
                c.loop = true;
            if (b.getAttribute("preload") == "none")
                c.preload = false;
            a && this.helpers.merge(c, a);
            if (c.createPlayer.markup)
                b = this.createPlayer(b, c.createPlayer, e);
            else
                b.parentNode.setAttribute("id", e);
            e = new g[o](b,c);
            c.css && this.helpers.injectCss(e, c.css);
            if (c.useFlash && c.hasFlash) {
                this.injectFlash(e, d);
                this.attachFlashEvents(e.wrapper, e)
            } else
                c.useFlash && !c.hasFlash && this.settings.flashError.apply(e);
            if (!c.useFlash || c.useFlash && c.hasFlash)
                this.attachEvents(e.wrapper, e);
            return this.instances[d] = e
        },
        createPlayer: function(b, a, c) {
            var d = document.createElement("div")
              , e = b.cloneNode(true);
            d.setAttribute("class", "audiojs");
            d.setAttribute("className", "audiojs");
            d.setAttribute("id", c);
            if (e.outerHTML && !document.createElement("audio").canPlayType) {
                e = this.helpers.cloneHtml5Node(b);
                d.innerHTML = a.markup;
                d.appendChild(e);
                b.outerHTML = d.outerHTML;
                d = document.getElementById(c)
            } else {
                d.appendChild(e);
                d.innerHTML += a.markup;
                b.parentNode.replaceChild(d, b)
            }
            return d.getElementsByTagName("audio")[0]
        },
        attachEvents: function(b, a) {
            if (a.settings.createPlayer) {
                var c = a.settings.createPlayer
                  , d = j(c.playPauseClass, b)
                  , e = j(c.scrubberClass, b);
                g[h].events.addListener(d, "click", function() {
                    a.playPause.apply(a)
                });
                g[h].events.addListener(e, "click", function(i) {
                    i = i.clientX;
                    var f = this
                      , k = 0;
                    if (f.offsetParent) {
                        do
                            k += f.offsetLeft;
                        while (f = f.offsetParent)
                    }
                    a.skipTo((i - k) / e.offsetWidth)
                });
                if (!a.settings.useFlash) {
                    g[h].events.trackLoadProgress(a);
                    g[h].events.addListener(a.element, "timeupdate", function() {
                        a.updatePlayhead.apply(a)
                    });
                    g[h].events.addListener(a.element, "ended", function() {
                        a.trackEnded.apply(a)
                    });
                    g[h].events.addListener(a.source, "error", function() {
                        clearInterval(a.readyTimer);
                        clearInterval(a.loadTimer);
                        a.settings.loadError.apply(a)
                    })
                }
            }
        },
        attachFlashEvents: function(b, a) {
            a.swfReady = false;
            a.load = function(c) {
                a.mp3 = c;
                a.swfReady && a.element.load(c)
            }
            ;
            a.loadProgress = function(c, d) {
                a.loadedPercent = c;
                a.duration = d;
                a.settings.loadStarted.apply(a);
                a.settings.loadProgress.apply(a, [c])
            }
            ;
            a.skipTo = function(c) {
                if (!(c > a.loadedPercent)) {
                    a.updatePlayhead.call(a, [c]);
                    a.element.skipTo(c)
                }
            }
            ;
            a.updatePlayhead = function(c) {
                a.settings.updatePlayhead.apply(a, [c])
            }
            ;
            a.play = function() {
                if (!a.settings.preload) {
                    a.settings.preload = true;
                    a.element.init(a.mp3)
                }
                a.playing = true;
                a.element.pplay();
                a.settings.play.apply(a)
            }
            ;
            a.pause = function() {
                a.playing = false;
                a.element.ppause();
                a.settings.pause.apply(a)
            }
            ;
            a.setVolume = function(c) {
                a.element.setVolume(c)
            }
            ;
            a.loadStarted = function() {
                a.swfReady = true;
                a.settings.preload && a.element.init(a.mp3);
                a.settings.autoplay && a.play.apply(a)
            }
        },
        injectFlash: function(b, a) {
            var c = this.flashSource.replace(/\$1/g, a);
            c = c.replace(/\$2/g, b.settings.swfLocation);
            c = c.replace(/\$3/g, +new Date + Math.random());
            var d = b.wrapper.innerHTML
              , e = document.createElement("div");
            e.innerHTML = c + d;
            b.wrapper.innerHTML = e.innerHTML;
            b.element = this.helpers.getSwf(a)
        },
        helpers: {
            merge: function(b, a) {
                for (attr in a)
                    if (b.hasOwnProperty(attr) || a.hasOwnProperty(attr))
                        b[attr] = a[attr]
            },
            clone: function(b) {
                if (b == null || typeof b !== "object")
                    return b;
                var a = new b.constructor, c;
                for (c in b)
                    a[c] = arguments.callee(b[c]);
                return a
            },
            addClass: function(b, a) {
                RegExp("(\\s|^)" + a + "(\\s|$)").test(b.className) || (b.className += " " + a)
            },
            removeClass: function(b, a) {
                b.className = b.className.replace(RegExp("(\\s|^)" + a + "(\\s|$)"), " ")
            },
            injectCss: function(b, a) {
                for (var c = "", d = document.getElementsByTagName("style"), e = a.replace(/\$1/g, b.settings.imageLocation), i = 0, f = d.length; i < f; i++) {
                    var k = d[i].getAttribute("title");
                    if (k && ~k.indexOf("audiojs")) {
                        f = d[i];
                        if (f.innerHTML === e)
                            return;
                        c = f.innerHTML;
                        break
                    }
                }
                d = document.getElementsByTagName("head")[0];
                i = d.firstChild;
                f = document.createElement("style");
                if (d) {
                    f.setAttribute("type", "text/css");
                    f.setAttribute("title", "audiojs");
                    if (f.styleSheet)
                        f.styleSheet.cssText = c + e;
                    else
                        f.appendChild(document.createTextNode(c + e));
                    i ? d.insertBefore(f, i) : d.appendChild(styleElement)
                }
            },
            cloneHtml5Node: function(b) {
                var a = document.createDocumentFragment()
                  , c = a.createElement ? a : document;
                c.createElement("audio");
                c = c.createElement("div");
                a.appendChild(c);
                c.innerHTML = b.outerHTML;
                return c.firstChild
            },
            getSwf: function(b) {
                b = document[b] || window[b];
                return b.length > 1 ? b[b.length - 1] : b
            }
        },
        events: {
            memoryLeaking: false,
            listeners: [],
            addListener: function(b, a, c) {
                if (b.addEventListener)
                    b.addEventListener(a, c, false);
                else if (b.attachEvent) {
                    this.listeners.push(b);
                    if (!this.memoryLeaking) {
                        window.attachEvent("onunload", function() {
                            if (this.listeners)
                                for (var d = 0, e = this.listeners.length; d < e; d++)
                                    g[h].events.purge(this.listeners[d])
                        });
                        this.memoryLeaking = true
                    }
                    b.attachEvent("on" + a, function() {
                        c.call(b, window.event)
                    })
                }
            },
            trackLoadProgress: function(b) {
                if (b.settings.preload) {
                    var a, c;
                    b = b;
                    var d = /(ipod|iphone|ipad)/i.test(navigator.userAgent);
                    a = setInterval(function() {
                        if (b.element.readyState > -1)
                            d || b.init.apply(b);
                        if (b.element.readyState > 1) {
                            b.settings.autoplay && b.play.apply(b);
                            clearInterval(a);
                            c = setInterval(function() {
                                b.loadProgress.apply(b);
                                b.loadedPercent >= 1 && clearInterval(c)
                            })
                        }
                    }, 10);
                    b.readyTimer = a;
                    b.loadTimer = c
                }
            },
            purge: function(b) {
                var a = b.attributes, c;
                if (a)
                    for (c = 0; c < a.length; c += 1)
                        if (typeof b[a[c].name] === "function")
                            b[a[c].name] = null;
                if (a = b.childNodes)
                    for (c = 0; c < a.length; c += 1)
                        purge(b.childNodes[c])
            },
            ready: function() {
                return function(b) {
                    var a = window
                      , c = false
                      , d = true
                      , e = a.document
                      , i = e.documentElement
                      , f = e.addEventListener ? "addEventListener" : "attachEvent"
                      , k = e.addEventListener ? "removeEventListener" : "detachEvent"
                      , n = e.addEventListener ? "" : "on"
                      , m = function(l) {
                        if (!(l.type == "readystatechange" && e.readyState != "complete")) {
                            (l.type == "load" ? a : e)[k](n + l.type, m, false);
                            if (!c && (c = true))
                                b.call(a, l.type || l)
                        }
                    }
                      , q = function() {
                        try {
                            i.doScroll("left")
                        } catch (l) {
                            setTimeout(q, 50);
                            return
                        }
                        m("poll")
                    };
                    if (e.readyState == "complete")
                        b.call(a, "lazy");
                    else {
                        if (e.createEventObject && i.doScroll) {
                            try {
                                d = !a.frameElement
                            } catch (r) {}
                            d && q()
                        }
                        e[f](n + "DOMContentLoaded", m, false);
                        e[f](n + "readystatechange", m, false);
                        a[f](n + "load", m, false)
                    }
                }
            }()
        }
    };
    g[o] = function(b, a) {
        this.element = b;
        this.wrapper = b.parentNode;
        this.source = b.getElementsByTagName("source")[0] || b;
        this.mp3 = function(c) {
            var d = c.getElementsByTagName("source")[0];
            return c.getAttribute("src") || (d ? d.getAttribute("src") : null)
        }(b);
        this.settings = a;
        this.loadStartedCalled = false;
        this.loadedPercent = 0;
        this.duration = 1;
        this.playing = false
    }
    ;
    g[o].prototype = {
        updatePlayhead: function() {
            this.settings.updatePlayhead.apply(this, [this.element.currentTime / this.duration])
        },
        skipTo: function(b) {
            if (!(b > this.loadedPercent)) {
                this.element.currentTime = this.duration * b;
                this.updatePlayhead()
            }
        },
        load: function(b) {
            this.loadStartedCalled = false;
            this.source.setAttribute("src", b);
            this.element.load();
            this.mp3 = b;
            g[h].events.trackLoadProgress(this)
        },
        loadError: function() {
            this.settings.loadError.apply(this)
        },
        init: function() {
            this.settings.init.apply(this)
        },
        loadStarted: function() {
            if (!this.element.duration)
                return false;
            this.duration = this.element.duration;
            this.updatePlayhead();
            this.settings.loadStarted.apply(this)
        },
        loadProgress: function() {
            if (this.element.buffered != null && this.element.buffered.length) {
                if (!this.loadStartedCalled)
                    this.loadStartedCalled = this.loadStarted();
                this.loadedPercent = this.element.buffered.end(this.element.buffered.length - 1) / this.duration;
                this.settings.loadProgress.apply(this, [this.loadedPercent])
            }
        },
        playPause: function() {
            this.playing ? this.pause() : this.play()
        },
        play: function() {
            /(ipod|iphone|ipad)/i.test(navigator.userAgent) && this.element.readyState == 0 && this.init.apply(this);
            if (!this.settings.preload) {
                this.settings.preload = true;
                this.element.setAttribute("preload", "auto");
                g[h].events.trackLoadProgress(this)
            }
            this.playing = true;
            this.element.play();
            this.settings.play.apply(this)
        },
        pause: function() {
            this.playing = false;
            this.element.pause();
            this.settings.pause.apply(this)
        },
        setVolume: function(b) {
            this.element.volume = b
        },
        trackEnded: function() {
            this.skipTo.apply(this, [0]);
            this.settings.loop || this.pause.apply(this);
            this.settings.trackEnded.apply(this)
        }
    };
    var j = function(b, a) {
        var c = [];
        a = a || document;
        if (a.getElementsByClassName)
            c = a.getElementsByClassName(b);
        else {
            var d, e, i = a.getElementsByTagName("*"), f = RegExp("(^|\\s)" + b + "(\\s|$)");
            d = 0;
            for (e = i.length; d < e; d++)
                f.test(i[d].className) && c.push(i[d])
        }
        return c.length > 1 ? c : c[0]
    }
}
)("audiojs", "audiojsInstance", this);